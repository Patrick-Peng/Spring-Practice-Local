package com.aaxis.microservice.training.demo1.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxis.microservice.training.demo1.domain.User;
import com.aaxis.microservice.training.demo1.service.UserService;

@RestController
@RequestMapping("/rest")
public class RestUserController {
	
    @Autowired
    private UserService pUserService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/doLogin")
    public Map<String, Object> login(@RequestBody@Validated User pUser, BindingResult bindingResult){
    	Map<String, Object> result = new HashMap<>();
    	if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				result.put("errorMessage", error.getDefaultMessage());
				return result;
			}
		}
        User user = pUserService.findUserByUserName(pUser);
        if(user == null || !user.getPassword().equals(pUser.getPassword())){
        	result.put("errorMessege", "email or password inccorect");
            return result;
        }
        result.put("user", user);
        return result;
    }

    @PostMapping("/doRegist")
    public Map<String, Object> doRegist(@RequestBody@Validated User user, BindingResult bindingResult){
    	Map<String, Object> result = new HashMap<>();
    	if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				result.put("errorMessage", error.getDefaultMessage());
				return result;
			}
		}
    	try{
            pUserService.regist(user);
            result.put("user", user);
        } catch (Exception e){
            logger.error("regist error" + e.getMessage());
            result.put("errorMessage", "regist error");
        }
        return result;
    }
}
