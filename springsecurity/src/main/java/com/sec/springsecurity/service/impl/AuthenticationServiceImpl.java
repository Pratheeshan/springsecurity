package com.sec.springsecurity.service.impl;

import com.sec.springsecurity.config.JwtUtil;
import com.sec.springsecurity.repository.UserRepository;
import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.OtpRequest;
import com.sec.springsecurity.request.RegisterRequest;
import com.sec.springsecurity.request.VerifyOtpRequest;
import com.sec.springsecurity.service.AuthenticationService;
import com.sec.springsecurity.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sec.springsecurity.model.User;

import java.util.ArrayList;
import java.util.Optional;

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

    @Autowired
    private OtpService otpService;

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
            return "Invalid Login";
        }
    }

    @Override
    public String sendOtp(OtpRequest otpRequest) {
        Optional<User> user = userRepository.findByWhatsappNumber(otpRequest.getWhatsappNumber());
        if (user.isPresent()) {
            otpService.storeOtp(otpRequest.getWhatsappNumber());
            return "OTP sent successfully to " + otpRequest.getWhatsappNumber();
        } else {
            return "Phone number not found!";
        }
    }

    @Override
    public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        String phone = verifyOtpRequest.getWhatsappNumber();
        String otp = verifyOtpRequest.getOtpNumber();

        // Validate OTP
        boolean isValid = otpService.validateOtp(phone, otp);
        if (!isValid) {
            return "Invalid OTP!";
        }

        // Find user by phone number
        Optional<User> userOptional = userRepository.findByWhatsappNumber(phone);
        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        User user = userOptional.get();

        // Create an Authentication object manually
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());

        // Generate JWT token
        String token = jwtUtil.generate(authentication);
        return "OTP Verified Successfully! Token: " + token;
    }


}
