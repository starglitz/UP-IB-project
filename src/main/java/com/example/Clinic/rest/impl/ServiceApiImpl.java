package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.ServiceApi;
import com.example.Clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceApiImpl implements ServiceApi {

    @Autowired
    private ServiceService serviceService;

    @Override
    public ResponseEntity getClinicServices(Long id) {
        System.out.println("in API: " + serviceService.findByClinicId(id));
        return new ResponseEntity(serviceService.findByClinicId(id), HttpStatus.OK);
    }
}
