package com.sec.springsecurity.model;

import com.sec.springsecurity.enums.AccessLevel;
import com.sec.springsecurity.enums.CareerLevel;
import com.sec.springsecurity.enums.CivilStatus;
import com.sec.springsecurity.enums.EmploymentType;
import com.sec.springsecurity.enums.WorkEnvironment;
import com.sec.springsecurity.model.substaff.Bank;
import com.sec.springsecurity.model.substaff.Emergency;
import com.sec.springsecurity.model.substaff.Socialmedia;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Staff {
    @Id
    private String id;

    @NotNull
    private String memberId;

    private String image;

    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First Name must be greater the 2 characters")
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last Name must be greater the 2 characters")
    private String lastName;

    @NotNull(message = "NIC is mandatory")
    @Pattern(message="Enter a valid NIC number", regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$")
    private String nic;

    @NotNull(message = "Date of Birth is mandatory")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dob;


    @NotNull(message = "Joined Date is mandatory")
    private LocalDate joinedDate;

    private CivilStatus civilStatus;

    @NotNull(message = "Career Level is mandatory")
    private CareerLevel careerLevel;

    @NotNull(message = "Role is mandatory")
    private String role;

    @NotNull(message = "Work Environment is mandatory")
    private WorkEnvironment workEnvironment;

    @NotNull(message = "Employment Type is mandatory")
    private EmploymentType employmentType;

    @NotNull(message = "Access Level is mandatory")
    private AccessLevel accessLevel;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String personalEmail;

    @Email(message = "Incorrect office Mail", regexp = "^[a-zA-Z0-9._%+-]+@techserw\\.com$")
    private String officeEmail;

    private String personalPhone;

    @NotNull(message = "WhatsApp Number is mandatory")
    @Pattern(message = "Phone is not Valid", regexp = "^(?:7|0|(?:\\+94))[0-9]{9,10}$")
    private String whatsappNumber;

    @NotNull(message = "Address is mandatory")
    private String address;

    private String district;

    private String province;

    @NotNull(message = "Country is mandatory")
    private String country;

    private Emergency emergency;
    private Socialmedia socialmedia;
    private Bank bank;


    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", member_id='" + memberId + '\'' +
                ", image='" + image + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", dob=" + dob +
                ", joined_date=" + joinedDate +
                ", civil_status=" + civilStatus +
                ", career_level=" + careerLevel +
                ", Role='" + role + '\'' +
                ", work_environment=" + workEnvironment +
                ", employment_type=" + employmentType +
                ", access_level=" + accessLevel +
                ", personal_email='" + personalEmail + '\'' +
                ", office_email='" + officeEmail + '\'' +
                ", personal_phone='" + personalPhone + '\'' +
                ", whatsapp_number='" + whatsappNumber + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", emergency=" + emergency +
                ", socialmedia=" + socialmedia +
                ", bank=" + bank +
                '}';
    }
    @DBRef
    private User user;
}
