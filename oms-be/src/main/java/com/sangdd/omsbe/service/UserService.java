package com.sangdd.omsbe.service;

import com.sangdd.omsbe.entity.User;
import com.sangdd.omsbe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }
    public List<User> getAllUsers() {
        return  repository.findAll();
    }

    public User createUser(User user) {
        return repository.save(user);
    }
}
