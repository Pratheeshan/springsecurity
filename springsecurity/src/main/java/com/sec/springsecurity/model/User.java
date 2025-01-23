package com.sec.springsecurity.model;
import com.sec.springsecurity.enums.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Document
@Setter
@Getter
public class User {

    @Id
    private String id;

    private String firstName;


    private String email;

    private String password;

    private AccessLevel accessLevel;

    public List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + accessLevel.name()));
    }

    public User() {
    }

    public User(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

}
