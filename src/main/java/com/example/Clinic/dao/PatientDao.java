package com.example.Clinic.dao;

import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao {

    public Patient addPatient(Patient patient);
    public List<Patient> getAll();

}
