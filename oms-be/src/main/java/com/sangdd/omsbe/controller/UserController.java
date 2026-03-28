package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.entity.User;
import com.sangdd.omsbe.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

}
