package com.mycinemalist.controller;

import com.mycinemalist.entity.User;
import com.mycinemalist.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        Optional<User> user = userService.findByUserId(userId);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
