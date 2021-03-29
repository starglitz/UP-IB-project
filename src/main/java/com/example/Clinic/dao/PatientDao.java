package com.example.Clinic.dao;

import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao {

    public Patient addPatient(Patient patient);

}
