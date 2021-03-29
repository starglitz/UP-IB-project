package com.example.Clinic.service;

import com.example.Clinic.model.Patient;

import java.util.List;

public interface PatientService {

   public Patient addPatient(Patient patient);
   public List<Patient> getALl();
}
