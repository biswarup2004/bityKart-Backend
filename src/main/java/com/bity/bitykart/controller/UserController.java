package com.bity.bitykart.controller;

import com.bity.bitykart.model.User;
import com.bity.bitykart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userservice;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userservice.registeruser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
            return userservice.loginUser(user.getEmail(),user.getPassword());
    }

    @GetMapping
    public List<User> getAllUsers(){
           return  userservice.getAllUser();
    }
}
