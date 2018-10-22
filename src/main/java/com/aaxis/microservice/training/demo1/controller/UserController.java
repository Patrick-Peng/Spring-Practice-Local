package com.aaxis.microservice.training.demo1.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aaxis.microservice.training.demo1.domain.User;
import com.aaxis.microservice.training.demo1.util.SpringUtil;

@Controller
public class UserController {
	
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/doLogin")
    public String login(@ModelAttribute User pUser, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Map<String, Object> loginResult = ((RestUserController) SpringUtil.getBean("restUserController")).login(pUser);
        if(loginResult.get("errorMessage") != null){
        	logger.debug("login error" + loginResult.get("errorMessage"));
        	redirectAttributes.addFlashAttribute("errorMessage", loginResult.get("errorMessage"));
            return "redirect:/login";
        }
        request.getSession().setAttribute("user", loginResult.get("user"));
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
    public String doRegist(@ModelAttribute User user, HttpServletRequest request, RedirectAttributes redirectAttributes){
        try{
            Map<String, Object> registResult = ((RestUserController) SpringUtil.getBean("restUserController")).doRegist(user);
            if (registResult.get("errorMessage") != null) {
            	logger.debug("regist error" + registResult.get("errorMessage"));
				redirectAttributes.addFlashAttribute("errorMessage",registResult.get("errorMessage"));
				return "redirect:/regist";
			}
        } catch (Exception e){
        	logger.error("regist error",e);	
        	redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/regist";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/index";
    }
}
