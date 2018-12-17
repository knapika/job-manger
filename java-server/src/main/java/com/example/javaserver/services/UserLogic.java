package com.example.javaserver.services;

import com.example.javaserver.entities.User;
import com.example.javaserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserLogic {
    @Autowired
    private UserRepository userRepository;

    public static final Integer STATUS_OK = 0;
    public static final Integer ERROR_LOGIN_EXISTS = 101;
    public static final Integer ERROR_EMAIL_EXISTS = 102;

    public Integer addUser(User userToAdd) {
        Integer status = this.validateUserToAdd(userToAdd);
        if(status == UserLogic.STATUS_OK) {
            userRepository.save(userToAdd);
        }
        return status;
    }

    private Integer validateUserToAdd(User user) {
        if(userRepository.findByLogin(user.getLogin()).isPresent()) {
            return ERROR_LOGIN_EXISTS;
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ERROR_EMAIL_EXISTS;
        }
        return STATUS_OK;
    }
}

