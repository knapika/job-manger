package com.example.javaserver.services;

import com.example.javaserver.entities.User;
import com.example.javaserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogic {
    @Autowired
    private UserRepository userRepository;

    public String addUser(User userToAdd) {
        List<User> users = userRepository.findByLogin(userToAdd.getLogin());
        if (users.size() == 0) {
            userRepository.save(userToAdd);
            return "Saved aa";
        }
        return "";
    }
}

