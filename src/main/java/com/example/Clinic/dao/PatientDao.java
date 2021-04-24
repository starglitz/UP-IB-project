package com.example.Clinic.dao;

import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDao {

    public Patient addPatient(Patient patient);
    public List<Patient> getAll();
    public Optional<Patient> getPatientById(Long id);
    public Patient updatePatient(Patient patient, Long id);


}
