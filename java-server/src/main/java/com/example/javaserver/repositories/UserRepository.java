package com.example.javaserver.repositories;

import com.example.javaserver.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}