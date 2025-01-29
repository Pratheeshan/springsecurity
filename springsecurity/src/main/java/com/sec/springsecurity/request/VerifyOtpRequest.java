package com.sec.springsecurity.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {

    private String personalPhone;
    private String otpNumber;
}
