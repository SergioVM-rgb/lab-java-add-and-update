package com.example.Lab404.repositories;

import com.example.Lab404.enums.EmployeeStatus;
import com.example.Lab404.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findByStatus(EmployeeStatus status);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByDepartmentAndStatus(String department, EmployeeStatus status);
}