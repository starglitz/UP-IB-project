package com.example.Clinic.service.impl;


import com.example.Clinic.model.Authority;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.User;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.repository.DoctorRepository;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findByClinicId(Long id) {
        return doctorRepository.findByClinicId(id);
    }

    @Override
    public List<Doctor> findByClinicAndDate(Long id, LocalDate date) {
        return doctorRepository.findByClinicAndDate(id, date);
    }

    @Override
    public Doctor create(Doctor doctor) {
        Optional<User> user = userRepository.findFirstByEmail(doctor.getUser().getEmail());

        if(user.isPresent()){
            return null;
        }

        Set<Authority> authorities = new HashSet<>(){{
            add(authorityRepository.findByName(UserRole.DOCTOR.toString()));
        }};

        User doctorUser = doctor.getUser();
        doctorUser.setRoles(authorities);
        doctorUser.setPassword(passwordEncoder.encode(doctorUser.getPassword()));
        doctorUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));

        doctor.setUser(doctorUser);
        Doctor doctorJpa = doctorRepository.save(doctor);
        return doctorJpa;
    }

    @Override
    public List<Doctor> getNotRatedByPatientId(Long id) {
        return doctorRepository.findNotRatedByPatientId(id);
    }
}
