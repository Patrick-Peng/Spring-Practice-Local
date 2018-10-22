package com.aaxis.microservice.training.demo1.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaxis.microservice.training.demo1.dao.UserDao;
import com.aaxis.microservice.training.demo1.domain.User;

@Service
public class UserService {
	@Autowired
	Validator validator;
    @Autowired
    private UserDao mUserDao;

    public void regist(User pUser) {
        User user = mUserDao.findByUsername(pUser.getUsername());
        if (user != null){
            throw new RuntimeException("User is exists in system");
        }
        mUserDao.save(pUser);
    }

    public User findUserByUserName(User pUser) {
        User user = mUserDao.findByUsername(pUser.getUsername());
        return user;
    }
    
    public Map<String, Object> validateUser(User user){
    	Map<String, Object> resultMap = new HashMap<>();
    	Set<ConstraintViolation<User>> validateResult = validator.validate(user);
    	for (ConstraintViolation<User> constraintViolation : validateResult) {
			String errorMsg = constraintViolation.getMessage();
			resultMap.put("errorMessage", errorMsg);
			return resultMap;
		}
    	return resultMap;
    }
}
