package com.example.Lab404.controllers.dto;

import jakarta.validation.constraints.NotEmpty;

public class DoctorDepartmentDTO {
    @NotEmpty(message = "Department can't be empty")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
