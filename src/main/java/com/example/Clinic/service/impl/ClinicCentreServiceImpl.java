package com.example.Clinic.service.impl;

import com.example.Clinic.model.ClinicCenter;
import com.example.Clinic.repository.ClinicCenterAdminRepository;
import com.example.Clinic.repository.ClinicCenterRepository;
import com.example.Clinic.service.ClinicCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicCentreServiceImpl implements ClinicCentreService {

    @Autowired
    private ClinicCenterRepository clinicCenterRepository;

    @Override
    public Optional<ClinicCenter> findById(Long id) {
        return clinicCenterRepository.findById(id);
    }
}
