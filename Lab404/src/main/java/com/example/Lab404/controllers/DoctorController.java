package com.example.Lab404.controllers;


import com.example.Lab404.controllers.dto.DoctorDTO;
import com.example.Lab404.controllers.dto.DoctorDepartmentDTO;
import com.example.Lab404.controllers.dto.DoctorStatusDTO;
import com.example.Lab404.enums.EmployeeStatus;
import com.example.Lab404.models.Doctor;
import com.example.Lab404.repositories.DoctorRepository;
import com.example.Lab404.services.interfaces.IDoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private IDoctorService doctorService;


    @GetMapping("/doctors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findById(@PathVariable String id) {
        return doctorRepository.findById(id).get();
    }

    @GetMapping("/doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> combinedSearch(@RequestParam Optional<String> department, @RequestParam Optional<EmployeeStatus> status) {
        if (department.isPresent() && status.isPresent()) {
            return doctorRepository.findByDepartmentAndStatus(department.get(), status.get());
        } else if (department.isPresent()) {
            return doctorRepository.findByDepartment(department.get());
        } else {
            return doctorRepository.findAll();
        }
    }

    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@RequestBody @Valid DoctorDTO doctorDTO) {
        return doctorService.create(doctorDTO);
    }

    @PatchMapping("/doctors/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void statusUpdate(@PathVariable String id, @RequestBody @Valid DoctorStatusDTO doctorStatusDTO) {
        doctorService.statusUpdate(id, doctorStatusDTO);
    }

    @PatchMapping("/doctors/{id}/department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void departmentUpdate(@PathVariable String id, @RequestBody @Valid DoctorDepartmentDTO doctorDepartmentDTO) {
        doctorService.departmentUpdate(id, doctorDepartmentDTO);
    }
}
