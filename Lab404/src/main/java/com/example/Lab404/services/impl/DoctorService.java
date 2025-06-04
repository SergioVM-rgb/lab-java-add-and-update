package com.example.Lab404.services.impl;


import com.example.Lab404.controllers.dto.DoctorDTO;
import com.example.Lab404.controllers.dto.DoctorDepartmentDTO;
import com.example.Lab404.controllers.dto.DoctorStatusDTO;
import com.example.Lab404.models.Doctor;
import com.example.Lab404.repositories.DoctorRepository;
import com.example.Lab404.services.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {
    @Autowired
    public DoctorRepository doctorRepository;

    public Doctor create(DoctorDTO doctorDTO) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorDTO.getEmployeeId());
        if (doctor.isEmpty()) {
            try {
                Doctor newDoctor = new Doctor(doctorDTO.getEmployeeId(), doctorDTO.getName(), doctorDTO.getDepartment(), doctorDTO.getEmployeeStatus());
                return doctorRepository.save(newDoctor);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input not valid, review the data and try again, it's probably status");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor already registered under that ID");
        }
    }

    public void statusUpdate(String id, DoctorStatusDTO statusDTO) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            try {
                doctor.get().setStatus(statusDTO.getEmployeeStatus());
                doctorRepository.save(doctor.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status must be ON, OFF or ON_CALL");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No doctors registered under that ID");
        }
    }

    public void departmentUpdate(String id, DoctorDepartmentDTO departmentDTO) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            try {
                doctor.get().setDepartment(departmentDTO.getDepartment());
                doctorRepository.save(doctor.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department not valid");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No doctors registered under that ID");
        }
    }
}