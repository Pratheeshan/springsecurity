package com.sec.springsecurity.service;

import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.RegisterRequest;

public interface AuthenticationService {
    public String register(RegisterRequest registerRequest);
    public String login(LoginRequest loginRequest);
}
