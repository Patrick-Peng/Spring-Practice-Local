package com.aaxis.microservice.training.demo1.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aaxis.microservice.training.demo1.domain.User;
import com.aaxis.microservice.training.demo1.service.UserService;
import com.aaxis.microservice.training.demo1.util.SpringUtil;

@Controller
public class UserController {

    @Autowired
    private UserService pUserService;
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/doLogin")
    public String login(@ModelAttribute User pUser, HttpServletRequest request, RedirectAttributes redirectAttributes){
        User user = ((RestUserController) SpringUtil.getBean("restUserController")).login(pUser);
        if(user == null){
        	logger.debug("user not exist");
        	redirectAttributes.addFlashAttribute("errorMessage","Login error");
            return "redirect:/login";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().removeAttribute("user");

        return "redirect:/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @PostMapping("/doRegist")
    public String doRegist(@ModelAttribute@Validated User user,BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes){
    	if (bindingResult.hasErrors()) {
        	for (FieldError fieldError : bindingResult.getFieldErrors()) {
                redirectAttributes.addFlashAttribute("errorMessage",fieldError.getDefaultMessage());
                logger.debug("regist failed", fieldError.getDefaultMessage());
                return "redirect:/regist";
            }
		}
        try{
            ((RestUserController) SpringUtil.getBean("restUserController")).doRegist(user);
        } catch (Exception e){
        	logger.error("regist error",e);
            request.setAttribute("errorMessage", e.getMessage());
            return "forward:/regist";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/index";
    }
}
