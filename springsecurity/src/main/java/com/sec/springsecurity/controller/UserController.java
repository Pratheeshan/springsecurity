package com.sec.springsecurity.controller;

import com.sec.springsecurity.response.UserResponse;
import com.sec.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("get-user/{email}")
    private UserResponse getUser(@PathVariable String email) {
        return userService.getUser(email);
    }
}
