package com.example.Clinic.service;

import com.example.Clinic.model.ClinicCenterAdmin;
import org.springframework.stereotype.Service;

@Service
public interface ClinicCentreAdminService {
    ClinicCenterAdmin findById(Long id);
}
