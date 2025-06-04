package com.example.Lab404.services.interfaces;

import com.example.Lab404.controllers.dto.PatientDTO;
import com.example.Lab404.models.Patient;

import java.text.ParseException;

public interface IPatientService {
    Patient create(PatientDTO patientDTO) throws ParseException;
    public void update(int id, PatientDTO patientDTO);
}
