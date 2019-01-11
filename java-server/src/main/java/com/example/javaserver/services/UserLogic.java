package com.example.javaserver.services;

import com.example.javaserver.dtos.ReportDTO;
import com.example.javaserver.entities.User;
import com.example.javaserver.repositories.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.example.javaserver.utils.Consts.ERROR_EMAIL_EXISTS;
import static com.example.javaserver.utils.Consts.ERROR_LOGIN_EXISTS;
import static com.example.javaserver.utils.Consts.STATUS_OK;

@Service
public class UserLogic {
    @Autowired
    private UserRepository userRepository;


    public ReportDTO<User> addUser(User userToAdd) {
        Integer status = this.validateUserToAdd(userToAdd);
        if(status == STATUS_OK) {
            String hashedPass = Hashing.sha256()
                    .hashString(userToAdd.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            userToAdd.setPassword(hashedPass);
            userRepository.save(userToAdd);
            userToAdd.setUserID(userRepository.findByLogin(userToAdd.getLogin()).get().getUserID());
        }
        userToAdd.setPassword("");
        return new ReportDTO<>(userToAdd, status);
    }

    public User login(User user) {
        Optional<User> userDB = this.userRepository.findByLogin(user.getLogin());
        if (userDB.isPresent()) {
            String hashedPassword = Hashing.sha256()
                    .hashString(user.getPassword(), StandardCharsets.UTF_8)
                    .toString();

            if (hashedPassword.equals(userDB.get().getPassword())) {
                user.setPassword("");
                user.setUserID(userDB.get().getUserID());
                return user;
            }
        }
        return null;
    }

    private Integer validateUserToAdd(User user) {
        if(userRepository.findByLogin(user.getLogin()).isPresent()) {
            return ERROR_LOGIN_EXISTS;
        }

        return STATUS_OK;
    }
}

