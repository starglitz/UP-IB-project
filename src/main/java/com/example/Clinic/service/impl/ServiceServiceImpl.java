package com.example.Clinic.service.impl;

import com.example.Clinic.model.Service;
import com.example.Clinic.repository.ServiceRepository;
import com.example.Clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> findByClinicId(Long clinic_id) {

        return serviceRepository.findByClinicId(clinic_id);
    }

    @Override
    public Optional<Service> findOne(Long id) {
        return serviceRepository.findById(id);
    }
}
