package com.example.Clinic.service;

import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PatientService {

   public Patient addPatient(Patient patient);
   public List<Patient> getAll();
   public Optional<Patient> getPatientById(Long id);
   public  Patient updatePatient(Patient patient, Long id);
   List<Patient> getByDoctorId(Long id);
}
