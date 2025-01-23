package com.sec.springsecurity.response;

import com.sec.springsecurity.model.Staff;

import java.util.List;

public class StaffResponse {
    private List<Staff> staff;

    public StaffResponse(List<Staff> staff) {
        this.staff = staff;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaffs(List<Staff> staff) {
        this.staff = staff;
    }
}
