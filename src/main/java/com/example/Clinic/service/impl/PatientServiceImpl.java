package com.example.Clinic.service.impl;


import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.PatientRepository;
import com.example.Clinic.security.services.AsymmetricEncription;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
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

        AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());

        patient.setLbo(encription.encrypt());


        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient, Long id) {
        Patient jpa = patientRepository.findById(id).orElse(null);

        AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
        jpa.setLbo(encription.encrypt());
        jpa.setVisitedMail(patient.isVisitedMail());

        return patientRepository.save(jpa);
    }

    @Override
    public List<Patient> getByDoctorId(Long id) {
        List<Patient> patients = patientRepository.findByDoctorId(id);


        for(Patient patient : patients){
            AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
            patient.setLbo(encription.decrypt());
        }
        System.out.println(patients);
        return patients;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = patientRepository.findAll();

        for(Patient patient : patients){
            AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
            patient.setLbo(encription.decrypt());
        }

        return patients;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        if(patient != null){
            AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
            patient.setLbo(encription.decrypt());
        }

        return Optional.ofNullable(patient);
    }
}
