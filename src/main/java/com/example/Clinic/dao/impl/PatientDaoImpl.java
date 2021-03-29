package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.Patient;
import com.example.Clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientDaoImpl implements PatientDao {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {return this.patientRepository.findById(id); }
}
