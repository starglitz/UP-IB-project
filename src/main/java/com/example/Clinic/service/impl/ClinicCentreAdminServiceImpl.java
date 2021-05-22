package com.example.Clinic.service.impl;

import com.example.Clinic.model.ClinicCenterAdmin;
import com.example.Clinic.repository.ClinicCenterAdminRepository;
import com.example.Clinic.service.ClinicCentreAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicCentreAdminServiceImpl implements ClinicCentreAdminService {

    @Autowired
    private ClinicCenterAdminRepository clinicCenterAdminRepository;

    @Override
    public ClinicCenterAdmin findById(Long id) {
        Optional<ClinicCenterAdmin> clinicCenterAdmin =  clinicCenterAdminRepository.findById(id);
        return clinicCenterAdmin.orElse(null);
    }
}
