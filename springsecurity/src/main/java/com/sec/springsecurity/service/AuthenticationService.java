package com.sec.springsecurity.service;

import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.OtpRequest;
import com.sec.springsecurity.request.RegisterRequest;
import com.sec.springsecurity.request.VerifyOtpRequest;

public interface AuthenticationService {
    public String register(RegisterRequest registerRequest);
    public String login(LoginRequest loginRequest);
    public String sendOtp(OtpRequest otpRequest);
    public String verifyOtp(VerifyOtpRequest verifyOtpRequest);
}
