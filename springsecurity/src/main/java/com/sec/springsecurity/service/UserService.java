package com.sec.springsecurity.service;

import com.sec.springsecurity.response.UserResponse;

public interface UserService {
    public UserResponse getUser(String email);
}
