package com.example.Lab404.controllers;


import com.example.Lab404.controllers.dto.PatientDTO;
import com.example.Lab404.enums.EmployeeStatus;
import com.example.Lab404.models.Patient;
import com.example.Lab404.repositories.PatientRepository;
import com.example.Lab404.services.interfaces.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private IPatientService patientService;


    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findById(@PathVariable Integer id) {
        return patientRepository.findById(id).get();
    }

    @GetMapping("/patients/birth-date")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByBirthDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date end) {
        return patientRepository.findByBirthDate(start, end);
    }

    @GetMapping("/patients/doctor-department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByDoctorDepartment(@PathVariable String department) {
        return patientRepository.findByDepartmentAdmittedTo(department);
    }

    @GetMapping("/patients/off-doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByOffDoctor() {
        return patientRepository.findByOffDoctor(EmployeeStatus.OFF);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@RequestBody @Valid PatientDTO patientDTO) throws ParseException {
        return patientService.create(patientDTO);
    }

    @PutMapping("/patients/{id}")
    public void update(@PathVariable int id, @RequestBody @Valid PatientDTO patientDTO) {
        patientService.update(id, patientDTO);
    }
}