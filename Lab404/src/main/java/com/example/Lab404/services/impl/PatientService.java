package com.example.Lab404.services.impl;

import com.example.Lab404.controllers.dto.PatientDTO;
import com.example.Lab404.models.Doctor;
import com.example.Lab404.models.Patient;
import com.example.Lab404.repositories.DoctorRepository;
import com.example.Lab404.repositories.PatientRepository;
import com.example.Lab404.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Patient create(PatientDTO patientDTO) {
        Optional<Doctor> doctor = doctorRepository.findById(patientDTO.getDoctorId());
        if (doctor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No doctors registered under that ID");
        }

        List<Patient> patients = patientRepository.findAll();
        Patient newPatient;
        try {
            newPatient = new Patient(patientDTO.getName(), new SimpleDateFormat("yyyy-MM-dd").parse(patientDTO.getDateOfBirth()), doctor.get());
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date format must be yyyy-mm-dd");
        }
        if (patients.contains(newPatient)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient already registered");
        } else {
            return patientRepository.save(newPatient);
        }
    }

    public void update(int id, PatientDTO patientDTO) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            if (patientDTO.getName() != null) {
                patient.get().setName(patientDTO.getName());
            }

            if (patientDTO.getDateOfBirth() != null) {
                try {
                    patient.get().setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(patientDTO.getDateOfBirth()));
                } catch (ParseException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date format must be yyyy-mm-dd");
                }
            }

            if (patientDTO.getDoctorId() != null) {
                Optional<Doctor> doctor = doctorRepository.findById(patientDTO.getDoctorId());
                if (doctor.isPresent()) {
                    patient.get().setAdmittedBy(doctor.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No doctors registered under that ID");
                }
            }
            patientRepository.save(patient.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No patient registered under that Id");
        }
    }
}
