package com.example.Clinic.service.impl;


import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.PatientRepository;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient, Long id) {
        Patient jpa = patientRepository.findById(id).orElse(null);
        jpa.setVisitedMail(patient.isVisitedMail());
        return patientRepository.save(jpa);
    }

    @Override
    public List<Patient> getByDoctorId(Long id) {
        return patientRepository.findByDoctorId(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) { return  patientRepository.findById(id);}
}
