package com.sec.springsecurity.request;

import com.sec.springsecurity.enums.AccessLevel;
import lombok.Data;

@Data
public class VerifyOtpRequest {

    private String whatsappNumber;
    private String otpNumber;
    private AccessLevel accessLevel;
}
