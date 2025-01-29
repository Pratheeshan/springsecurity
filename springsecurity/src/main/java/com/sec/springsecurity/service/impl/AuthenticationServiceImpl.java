package com.sec.springsecurity.service.impl;

import com.sec.springsecurity.config.JwtUtil;
import com.sec.springsecurity.repository.UserRepository;
import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.OtpRequest;
import com.sec.springsecurity.request.RegisterRequest;
import com.sec.springsecurity.request.VerifyOtpRequest;
import com.sec.springsecurity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sec.springsecurity.model.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest registerRequest) { //register a new user by saving their details in the database
        User user = new User(
                registerRequest.getFirstName(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(LoginRequest loginRequest) { //authenticate an existing user and generate a JWT after successful authentication
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            // Avoid direct recursion, and instead inject AuthenticationManager where needed
            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtil.generate(authentication);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @Override
    public String sendOtp(OtpRequest otpRequest) {
        return "";
    }

    @Override
    public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        return "";
    }


}
