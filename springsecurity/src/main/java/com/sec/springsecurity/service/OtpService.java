package com.sec.springsecurity.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private Map<String, String> otpStorage = new HashMap<>();


    public String generateOtp() {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return otp.toString(); // Return OTP as String
    }

    public void storeOtp(String personalPhone) {
        String otp = new String(generateOtp()); // Generate OTP
        otpStorage.put(personalPhone, otp); // Store OTP
        System.out.println("Data type: "+otp.getClass().getSimpleName());
        System.out.println("Stored OTP for " + personalPhone + " is: " + otp);
    }


    public boolean validateOtp(String personalPhone, String enteredOtp) {
        if (!otpStorage.containsKey(personalPhone)) {
            return false; // OTP not found
        }

        String storedOtp = otpStorage.get(personalPhone);
        boolean isValid = storedOtp.equals(enteredOtp);

        if (isValid) {
            otpStorage.remove(personalPhone); // Remove OTP after successful validation
        }

        return isValid;
    }



}
