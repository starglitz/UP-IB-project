package com.example.Clinic.service;

import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PatientService {

   public boolean addPatient(Patient patient);
   public List<Patient> getALl();
   public Optional<Patient> getPatientById(Long id);
}
