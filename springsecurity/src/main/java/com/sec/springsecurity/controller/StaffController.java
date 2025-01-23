package com.sec.springsecurity.controller;

import com.sec.springsecurity.model.Staff;
import com.sec.springsecurity.model.substaff.ApiResponse;
import com.sec.springsecurity.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> saveStaff(@Valid @RequestBody Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Validation Failed", String.join(", ", errorMessages))
            );
        }

        ApiResponse<String> response = staffService.saveStaff(staff);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Staff>>> getAllStaff() {
        ApiResponse<List<Staff>> response = staffService.getAllStaff();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Staff>> getStaffById(@PathVariable String id) {
        ApiResponse<Staff> response = staffService.getStaffById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStaffById(@PathVariable String id) {
        ApiResponse<String> response = staffService.deleteStaffById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
