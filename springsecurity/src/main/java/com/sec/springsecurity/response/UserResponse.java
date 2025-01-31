package com.sec.springsecurity.response;

import com.sec.springsecurity.enums.AccessLevel;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String whatsappNumber;

    public UserResponse() {
    }

    public UserResponse(String id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public UserResponse(String id, AccessLevel accessLevel, String whatsappNumber) {
    }

}
