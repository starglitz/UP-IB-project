package com.example.Clinic.service.impl;

import com.example.Clinic.dao.DoctorDao;
import com.example.Clinic.dao.NurseDao;
import com.example.Clinic.dao.PatientDao;
import com.example.Clinic.model.*;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
