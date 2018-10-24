package com.aaxis.microservice.training.demo1.service;

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
    
}
