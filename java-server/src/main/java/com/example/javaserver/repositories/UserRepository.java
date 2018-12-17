package com.example.javaserver.repositories;

import com.example.javaserver.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByLogin(String login);
}