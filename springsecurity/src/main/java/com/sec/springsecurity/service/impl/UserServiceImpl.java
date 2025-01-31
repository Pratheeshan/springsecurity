package com.sec.springsecurity.service.impl;

import com.sec.springsecurity.model.User;
import com.sec.springsecurity.repository.UserRepository;
import com.sec.springsecurity.response.UserResponse;
import com.sec.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//business logic related to user management
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getUser(String email) {
        User user = userRepository.findByEmail(email); //findByEmail method of the userRepository to fetch a User from the database.
        return new UserResponse(
                user.getId(),
                user.getFirstName()
                //Returns a UserResponse object with specific user information
        );
    }

    @Override
    public UserResponse getUserByPhoneNumber(String whatsappNumber) {
        Optional<User> user = userRepository.findByWhatsappNumber(whatsappNumber);
        return new UserResponse();
    }
}
