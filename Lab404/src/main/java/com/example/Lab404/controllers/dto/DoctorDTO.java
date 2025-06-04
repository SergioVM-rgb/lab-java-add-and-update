package com.example.Lab404.controllers.dto;

import com.example.Lab404.enums.EmployeeStatus;
import jakarta.validation.constraints.NotEmpty;

public class DoctorDTO {

    @NotEmpty(message = "EmployeeId can't be empty")
    private String employeeId;

    @NotEmpty(message = "Dude has to have a name")
    private String name;

    @NotEmpty(message = "Department can't be empty")
    private  String department;

    @NotEmpty(message = "Status can't be empty")
    private String status;

    public DoctorDTO(String employeeId, String name, String department, String status) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.status = employeeStatus.toString();
    }

    public EmployeeStatus getEmployeeStatus() {
        return EmployeeStatus.valueOf(status);
    }
}
