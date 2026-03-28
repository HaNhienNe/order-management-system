package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.entity.User;
import com.sangdd.omsbe.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping
    public List<User> getAllUsers () {
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(User user) {
        return service.createUser(user);
    }

}
