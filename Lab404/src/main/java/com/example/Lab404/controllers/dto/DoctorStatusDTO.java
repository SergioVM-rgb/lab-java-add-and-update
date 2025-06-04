package com.example.Lab404.controllers.dto;

import com.example.Lab404.enums.EmployeeStatus;
import jakarta.validation.constraints.NotEmpty;

public class DoctorStatusDTO {

    @NotEmpty(message = "Status can't be empty")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.status = employeeStatus.toString();
    }

    public EmployeeStatus getEmployeeStatus() {
        return EmployeeStatus.valueOf(status);
    }
}
