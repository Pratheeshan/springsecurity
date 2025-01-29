package com.sec.springsecurity.response;

import com.sec.springsecurity.enums.*;
import com.sec.springsecurity.model.User;
import com.sec.springsecurity.model.substaff.Bank;
import com.sec.springsecurity.model.substaff.Emergency;
import com.sec.springsecurity.model.substaff.Socialmedia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponse {
    private String id;
    private String memberId;
    private String image;
    private String firstName;
    private String lastName;
    private String nic;
    private LocalDate dob;
    private LocalDate joinedDate;
    private CivilStatus civilStatus;
    private CareerLevel careerLevel;
    private String role;
    private WorkEnvironment workEnvironment;
    private EmploymentType employmentType;
    private AccessLevel accessLevel;
    private String personalEmail;
    private String officeEmail;
    private String personalPhone;
    private String whatsappNumber;
    private String address;
    private String district;
    private String province;
    private String country;
    private Emergency emergency;
    private Socialmedia socialmedia;
    private Bank bank;
    private User user;
}
