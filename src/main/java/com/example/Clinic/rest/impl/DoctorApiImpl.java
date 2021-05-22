package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.rest.DoctorApi;
import com.example.Clinic.rest.support.converter.DoctorToDto;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorApiImpl implements DoctorApi {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorToDto doctorToDto;


    @Override
    public ResponseEntity getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctor(Long id) {
        Doctor doctor = doctorService.findById(id).orElse(null);
        if(doctor == null) {
            return new ResponseEntity("Doctor with id " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        DoctorDto dto = doctorToDto.convert(doctor);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctorsByClinicAndDate(Long id, LocalDate date) {
        List<Doctor> doctors = doctorService.findByClinicAndDate(id,date);
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctorByClinicId(Long id) {
        List<Doctor> doctors = doctorService.findByClinicId(id);
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }
}
