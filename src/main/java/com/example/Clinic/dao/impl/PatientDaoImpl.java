package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.Patient;
import com.example.Clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDaoImpl implements PatientDao {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }
}
