package com.sec.springsecurity.service;

import com.sec.springsecurity.response.UserResponse;

public interface UserService {
    public UserResponse getUser(String email);
    public UserResponse getUserByPhoneNumber(String whatsappNumber);
}
