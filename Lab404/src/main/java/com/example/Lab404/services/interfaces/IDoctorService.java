package com.example.Lab404.services.interfaces;

import com.example.Lab404.controllers.dto.DoctorDTO;
import com.example.Lab404.controllers.dto.DoctorDepartmentDTO;
import com.example.Lab404.controllers.dto.DoctorStatusDTO;
import com.example.Lab404.models.Doctor;

public interface IDoctorService {
    public Doctor create(DoctorDTO doctorDTO);
    void statusUpdate(String id, DoctorStatusDTO statusDTO);
    void departmentUpdate(String id, DoctorDepartmentDTO departmentDTO);
}
