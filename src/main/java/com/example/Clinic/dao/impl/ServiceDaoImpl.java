package com.example.Clinic.dao.impl;


import com.example.Clinic.dao.ServiceDao;
import com.example.Clinic.model.Service;
import com.example.Clinic.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<Service> findByClinicId(Long clinic_id) {
        return serviceRepository.findByClinicId(clinic_id);
    }
}
