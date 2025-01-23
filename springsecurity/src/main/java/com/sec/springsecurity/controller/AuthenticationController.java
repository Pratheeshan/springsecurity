package com.sec.springsecurity.controller;

import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.RegisterRequest;
import com.sec.springsecurity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("test")
    public String getHello() {
        return "Hello";
    }

    //user registration endpoint
    @PostMapping("register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    //login request endpoint
    @GetMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
