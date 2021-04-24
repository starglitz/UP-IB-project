package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.ClinicApi;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClinicApiImpl implements ClinicApi {

    @Autowired
    private ClinicService clinicService;

    @Override
    public ResponseEntity getAllClinics() {
        return new ResponseEntity(clinicService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getClinic(Long id) {
        if(clinicService.findById(id) != null) {
            return new ResponseEntity(clinicService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity("No such hospital ", HttpStatus.BAD_REQUEST);
    }
}
