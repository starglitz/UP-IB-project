package com.example.Clinic.service.impl;

import com.example.Clinic.dao.DoctorDao;
import com.example.Clinic.dao.NurseDao;
import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.*;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private NurseDao nurseDao;

    @Autowired
    private PatientDao patientDao;


    @Override
    public boolean checkPatientLogin(LoginForm loginForm) {

        List<Patient> patients = patientDao.getAll();
        for(Patient patient : patients){
            if(loginForm.getEmail().equals(patient.getEmail()) && BCrypt.checkpw(loginForm.getPassword(), patient.getPassword())){
                return true;
            }
        }
        return false;
    }
}
