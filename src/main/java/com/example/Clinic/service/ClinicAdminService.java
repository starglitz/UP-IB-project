package com.example.Clinic.service;

import com.example.Clinic.model.ClinicAdmin;
import org.springframework.stereotype.Service;

@Service
public interface ClinicAdminService {
    ClinicAdmin findById(Long id);
    ClinicAdmin save(ClinicAdmin clinicAdmin);
}
