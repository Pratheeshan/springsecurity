package com.sec.springsecurity.controller;

import com.sec.springsecurity.request.LoginRequest;
import com.sec.springsecurity.request.OtpRequest;
import com.sec.springsecurity.request.RegisterRequest;
import com.sec.springsecurity.request.VerifyOtpRequest;
import com.sec.springsecurity.service.AuthenticationService;
import com.sec.springsecurity.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OtpService otpService;

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

//    @PostMapping("send-otp")
//    public String sendOtp(@RequestBody OtpRequest otpRequest){
//        return authenticationService.sendOtp(otpRequest);
//    }
//    @GetMapping("verify-otp")
//    public String verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest){
//        return authenticationService.verifyOtp(verifyOtpRequest);
//    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest otpRequest) {  //0767202075
        // 2. Generate and store OTP for this phone number
        otpService.storeOtp(otpRequest.getPersonalPhone());
        // 3. Return success message
        return ResponseEntity.ok("OTP sent successfully to " + otpRequest.getPersonalPhone());
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        //Validate OTP
        boolean isValid = otpService.validateOtp(verifyOtpRequest.getPersonalPhone(), verifyOtpRequest.getOtpNumber());
        //  If OTP is valid, return success response
        if (isValid) {
            return ResponseEntity.ok("OTP Verified Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP!");
        }
    }


}
