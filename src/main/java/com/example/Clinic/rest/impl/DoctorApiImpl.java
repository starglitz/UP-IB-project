package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.DoctorApi;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorApiImpl implements DoctorApi {

    @Autowired
    private DoctorService doctorService;


    @Override
    public ResponseEntity getAllDoctors() {
        return new ResponseEntity(doctorService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctor(Long id) {
        return new ResponseEntity(doctorService.findById(id), HttpStatus.OK);
    }
}
