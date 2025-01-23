package com.sec.springsecurity.service;

import com.sec.springsecurity.model.Staff;
import com.sec.springsecurity.model.User;
import com.sec.springsecurity.model.substaff.ApiResponse;
import com.sec.springsecurity.repository.StaffRepository;
import com.sec.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse<String> saveStaff(Staff staff) {
        if (staff.getDob() != null && !isAdult(staff.getDob())) {
            return new ApiResponse<>(false, "Validation Failed", "Age must be 18 years or older");
        }

        try {
            String randomPass = generateRandomPassword(10);
            String encodedPassword = passwordEncoder.encode(randomPass);

            if (staff.getAccessLevel() == null) {
                return new ApiResponse<>(false, "Validation Failed", "AccessLevel is required");
            }

            User user = new User();
            user.setEmail(staff.getPersonalEmail());
            user.setFirstName(staff.getFirstName());
            user.setAccessLevel(staff.getAccessLevel());
            user.setPassword(encodedPassword);

            userRepository.save(user);

            staff.setUser(user);
            Staff savedStaff = staffRepository.save(staff);
            return new ApiResponse<>(true, "Staff created successfully", "Staff ID: " + savedStaff.getId());
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error creating staff", e.getMessage());
        }
    }
    private boolean isAdult(LocalDate dob) {
        return dob.isBefore(LocalDate.now().minusYears(18));
    }

    public ApiResponse<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        System.out.println("Staff retrieved: " + staffList);
        if (staffList.isEmpty()) {
            return new ApiResponse<>(true, "No staff records found", Collections.emptyList());
        }
        return new ApiResponse<>(true, "Staff records retrieved successfully", staffList);
    }


    public ApiResponse<Staff> getStaffById(String id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()) {
            return new ApiResponse<>(true, "Staff record retrieved successfully", staff.get());
        }
        return new ApiResponse<>(false, "Staff not found", null);
    }

    public ApiResponse<String> deleteStaffById(String id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return new ApiResponse<>(true, "Deleted Successfully", null);
        }
        return new ApiResponse<>(false, "Staff with id " + id + " not found.", null);
    }

    public static String generateRandomPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return ("Password");
    }

}
