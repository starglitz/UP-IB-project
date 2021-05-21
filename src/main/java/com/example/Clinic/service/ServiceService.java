package com.example.Clinic.service;


import com.example.Clinic.model.Service;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface ServiceService {

    public List<Service> findByClinicId(Long clinic_id);

    Optional<Service> findOne(Long id);
}
