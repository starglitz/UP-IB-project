package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.rest.ClinicApi;
import com.example.Clinic.rest.support.converter.ClinicToDto;
import com.example.Clinic.rest.support.dto.ClinicDto;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ClinicApiImpl implements ClinicApi {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicToDto clinicToDto;

    @Override
    public ResponseEntity getAllClinics() {
        return new ResponseEntity(clinicService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getClinic(Long id) {
        Clinic clinic = clinicService.findById(id);
        if(clinic != null) {
            ClinicDto dto = clinicToDto.convert(clinic);
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity("No such hospital ", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getClinicsByDate(LocalDate date) {
        if(clinicService.findClinicsByDate(date).size() > 0) {
            return new ResponseEntity(clinicService.findClinicsByDate(date), HttpStatus.OK);
        }
        return new ResponseEntity(clinicService.findClinicsByDate(date), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity update(Long id, @Valid Clinic clinic) {
        return  new ResponseEntity(clinicService.update(clinic), HttpStatus.OK);
    }


}
