package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.Patient;
import com.example.Clinic.repository.PatientRepository;
import com.example.Clinic.security.salt.BCrypt;
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

    @Override
    public Patient updatePatient(Patient patient, Long id) {


        Patient pat = patientRepository.findById(id).get();

        pat.setEmail(patient.getEmail());
        pat.setName(patient.getName());
        pat.setLastName(patient.getLastName());
        pat.setEmail(patient.getEmail());
        pat.setAddress(patient.getAddress());
        pat.setCity(patient.getCity());
        pat.setCountry(patient.getCountry());
        pat.setPhoneNumber(patient.getPhoneNumber());
        pat.setLbo(patient.getLbo());
        pat.setApproved(patient.isApproved());
        if(patient.getPassword().length() > 1){
            pat.setPassword(patient.getPassword());
        }
        pat.setEnabled(patient.isEnabled());
        return patientRepository.save(pat);
    }



}
