package com.example.Clinic.service;

import com.example.Clinic.model.ClinicCenter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClinicCentreService {
    Optional<ClinicCenter> findById(Long id);

}
