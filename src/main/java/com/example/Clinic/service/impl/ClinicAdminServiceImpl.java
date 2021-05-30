package com.example.Clinic.service.impl;

import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.repository.ClinicAdminRepository;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.service.ClinicAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicAdminServiceImpl implements ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public ClinicAdmin findById(Long id) {
        Optional<ClinicAdmin> clinicAdmin = clinicAdminRepository.findById(id);
        return clinicAdmin.orElse(null);
    }

    @Override
    public ClinicAdmin save(ClinicAdmin clinicAdmin) {
        userRepository.save(clinicAdmin.getUser());
        return clinicAdminRepository.save(clinicAdmin);
    }
}
