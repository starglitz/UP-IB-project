package com.example.Clinic.service;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.LoginForm;
import com.example.Clinic.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public boolean checkPatientLogin(LoginForm loginForm);

}
