package com.example.Clinic.service;

import com.example.Clinic.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
}
