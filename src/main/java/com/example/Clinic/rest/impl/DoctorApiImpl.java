package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.DoctorApi;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    @Override
    public ResponseEntity getDoctorsByClinicAndDate(Long id, LocalDate date) {
        return new ResponseEntity(doctorService.findByClinicAndDate(id,date), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctorByClinicId(Long id) {
        return new ResponseEntity(doctorService.findByClinicId(id), HttpStatus.OK);
    }
}
