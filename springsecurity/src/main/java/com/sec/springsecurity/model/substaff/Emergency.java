package com.sec.springsecurity.model.substaff;

import jakarta.validation.constraints.NotNull;

public class Emergency {

    @NotNull(message = "Emergency Person Name is mandatory")
    private String emergencyName;

    @NotNull(message = "Emergency Person Relationship is mandatory")
    private String emergencyRelationship;

    @NotNull(message = "Emergency Person Phone Number is mandatory")
    private String emergencyPhone;

    public Emergency(@NotNull String emergencyName, @NotNull String emergencyRelationship, @NotNull String emergencyPhone) {
        this.emergencyName = emergencyName;
        this.emergencyRelationship = emergencyRelationship;
        this.emergencyPhone = emergencyPhone;
    }

    public void setEmergencyName(@NotNull String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public void setEmergencyRelationship(@NotNull String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }

    public void setEmergencyPhone(@NotNull String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "emergency_name='" + emergencyName + '\'' +
                ", emergency_relationship='" + emergencyRelationship + '\'' +
                ", emergency_phone='" + emergencyPhone + '\'' +
                '}';
    }
}
