package com.example.Clinic.service;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.LoginForm;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public boolean checkPatientLogin(LoginForm loginForm);

    Optional<User> findUserByEmail(String email);

}
