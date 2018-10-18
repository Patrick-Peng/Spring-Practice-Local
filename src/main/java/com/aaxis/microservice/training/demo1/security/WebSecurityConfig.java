//package com.aaxis.microservice.training.demo1.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin().loginPage("/login").and().authorizeRequests()
//				.antMatchers("/doLogin","/login").permitAll().anyRequest().authenticated()
//				.and().csrf().disable();
//	}
//}
