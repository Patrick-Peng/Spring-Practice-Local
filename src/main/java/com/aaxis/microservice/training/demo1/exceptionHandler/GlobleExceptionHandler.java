package com.aaxis.microservice.training.demo1.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@ControllerAdvice
public class GlobleExceptionHandler {
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public void MethodArgumentNotValidHandler(HttpServletRequest request,MethodArgumentNotValidException exception,RedirectAttributes redirectAttributes){
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage",fieldError.getDefaultMessage());
        }
	}

}
