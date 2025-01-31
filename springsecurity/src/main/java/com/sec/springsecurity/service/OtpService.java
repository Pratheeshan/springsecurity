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

        for (int i = 0; i < 6; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return otp.toString(); // Return OTP as String
    }

    public void storeOtp(String whatsappNumber) {
        if (otpStorage.containsKey(whatsappNumber)) {
            System.out.println("OTP already exists for this number. Please wait.");
            return; // Prevent overwriting OTP if it's still valid
        }
        String otp = generateOtp(); // Generate OTP
        otpStorage.put(whatsappNumber, otp); // Store OTP
        //System.out.println("Data type: "+otp.getClass().getSimpleName());
        System.out.println("Stored OTP for " + whatsappNumber + " is: " + otp);
    }


    public boolean validateOtp(String whatsappNumber, String enteredOtp) {
        if (!otpStorage.containsKey(whatsappNumber)) {
            return false; // OTP not found
        }

        String storedOtp = otpStorage.get(whatsappNumber);
        boolean isValid = storedOtp.equals(enteredOtp);

        if (isValid) {
            otpStorage.remove(whatsappNumber); // Remove OTP after successful validation
        }

        return isValid;
    }



}
