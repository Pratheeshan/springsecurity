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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
