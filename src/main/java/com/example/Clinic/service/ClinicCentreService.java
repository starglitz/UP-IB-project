package com.example.Clinic.service;

import com.example.Clinic.model.ClinicCenter;
import org.springframework.stereotype.Service;

@Service
public interface ClinicCentreService {
    ClinicCenter findById(Long id);
}
