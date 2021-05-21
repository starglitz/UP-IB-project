package com.example.Clinic.service.impl;


import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.PatientRepository;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public boolean addPatient(Patient patient) {
        boolean valid = true;
//        if(patient.getName().isEmpty() || patient.getName() == null) {
//            valid = false;
//        }
//
//        if(patient.getLastName().isEmpty() || patient.getLastName() == null) {
//            valid = false;
//        }
//
//        if(patient.getEmail().isEmpty() || patient.getEmail() == null) {
//            valid = false;
//        }
//
//        if(patient.getPassword().isEmpty() || patient.getPassword() == null) {
//            valid = false;
//        }
//
//        if(patient.getLbo().isEmpty() || patient.getLbo() == null) {
//            valid = false;
//        }
//
//        if(patient.getAddress().isEmpty() || patient.getAddress() == null) {
//            valid = false;
//        }
//
//        if(patient.getCity().isEmpty() || patient.getCity() == null) {
//            valid = false;
//        }
//
//        if(patient.getCountry().isEmpty() || patient.getCountry() == null) {
//            valid = false;
//        }
//
//        if(patient.getPhoneNumber().isEmpty() || patient.getPhoneNumber() == null) {
//            valid = false;
//        }
//
//        if (valid) {
//            String hashedPw = BCrypt.hashpw(patient.getPassword(), BCrypt.gensalt());//10
//            patient.setPassword(hashedPw);
//
//            PatientBook patientBook = new PatientBook();
//            patientBook.setPatient(patient);
//
//            patientBookDao.addPatientBook(patientBook);
//            patientDao.addPatient(patient);
//        }
        return valid;

    }

    @Override
    public boolean updatePatient(Patient patient, Long id) {
        boolean valid = true;
//        if(patient.getName().isEmpty() || patient.getName() == null) {
//            valid = false;
//        }
//
//        if(patient.getLastName().isEmpty() || patient.getLastName() == null) {
//            valid = false;
//        }
//
//        if(patient.getEmail().isEmpty() || patient.getEmail() == null) {
//            valid = false;
//        }
//
//        if(patient.getLbo().isEmpty() || patient.getLbo() == null) {
//            valid = false;
//        }
//
//        if(patient.getAddress().isEmpty() || patient.getAddress() == null) {
//            valid = false;
//        }
//
//        if(patient.getCity().isEmpty() || patient.getCity() == null) {
//            valid = false;
//        }
//
//        if(patient.getCountry().isEmpty() || patient.getCountry() == null) {
//            valid = false;
//        }
//
//        if(patient.getPhoneNumber().isEmpty() || patient.getPhoneNumber() == null) {
//            valid = false;
//        }
//
//        if (valid) {
//            System.out.println(patient.getPassword());
//            if(patient.getPassword().length() >= 8){
//                String hashedPw = BCrypt.hashpw(patient.getPassword(), BCrypt.gensalt());//10
//                patient.setPassword(hashedPw);
//                System.out.println("dasdsa" + patient.getPassword());
//            }
//            patientDao.updatePatient(patient, id);
//
//        }
        return valid;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) { return  patientRepository.findById(id);}
}
