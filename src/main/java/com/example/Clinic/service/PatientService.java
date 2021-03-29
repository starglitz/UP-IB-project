package com.example.Clinic.service;

import com.example.Clinic.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

   public Patient addPatient(Patient patient);
   public List<Patient> getALl();
   public Optional<Patient> getPatientById(Long id);
}
