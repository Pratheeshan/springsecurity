package com.sec.springsecurity.request;

import com.sec.springsecurity.enums.AccessLevel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String email;
    private String password;
    private AccessLevel accessLevel;
    private String whatsappNumber;
}
