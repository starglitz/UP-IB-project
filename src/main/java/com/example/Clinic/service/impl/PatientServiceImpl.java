package com.example.Clinic.service.impl;

import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.Patient;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public Patient addPatient(Patient patient) {
        return patientDao.addPatient(patient);
    }

    @Override
    public List<Patient> getALl() {
        return patientDao.getAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) { return  patientDao.getPatientById(id);}
}
