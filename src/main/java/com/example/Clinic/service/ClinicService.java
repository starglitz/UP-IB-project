package com.example.Clinic.service;

import com.example.Clinic.model.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    List<Clinic> findAll();
    Clinic findById(Long id);
}
