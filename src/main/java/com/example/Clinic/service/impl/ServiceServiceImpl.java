package com.example.Clinic.service.impl;

import com.example.Clinic.dao.ServiceDao;
import com.example.Clinic.model.Service;
import com.example.Clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Override
    public List<Service> findByClinicId(Long clinic_id) {
        System.out.println(serviceDao.findByClinicId(clinic_id));
        return serviceDao.findByClinicId(clinic_id);
    }
}
