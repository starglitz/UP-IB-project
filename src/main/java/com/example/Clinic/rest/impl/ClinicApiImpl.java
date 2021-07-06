package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.rest.ClinicApi;
import com.example.Clinic.rest.support.converter.ClinicToDto;
import com.example.Clinic.rest.support.converter.DtoToClinic;
import com.example.Clinic.rest.support.dto.ClinicDto;
import com.example.Clinic.security.services.AsymmetricEncription;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ClinicApiImpl implements ClinicApi {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DtoToClinic toClinic;

    @Autowired
    private ClinicToDto clinicToDto;

    @Autowired
    private UserService userService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private PatientService patientService;

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
        List<Clinic> clinics = clinicService.findClinicsByDate(date);
        if(clinics.size() > 0) {
            return new ResponseEntity(clinicToDto.convertList(clinics), HttpStatus.OK);
        }
        return new ResponseEntity(clinicToDto.convertList(clinics), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity create(@Valid ClinicDto dto) {
        System.out.println(dto);
        System.out.println(dto);
        System.out.println(dto);
        System.out.println(dto);
        System.out.println(dto);
        System.out.println(dto);
        System.out.println(dto);
        Clinic clinic = toClinic.convert(dto);
        if (clinic != null)
            return new ResponseEntity(clinicService.create(clinic), HttpStatus.CREATED);
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity update(Long id, @Valid Clinic clinic) {
        return  new ResponseEntity(clinicService.update(clinic), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getClinicByLoggedInAdmin(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        ClinicAdmin admin = clinicAdminService.findById(user.getId());
        System.out.println(user);
        System.out.println(admin.getClinic());
        Clinic clinic = admin.getClinic();

        return new ResponseEntity(clinicToDto.convert(admin.getClinic()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNotRatedVisitedClinics(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        List<Clinic> clinics = clinicService.getNotRatedByPatientId(user.getId());
        if(clinics.size() > 0) {
            return new ResponseEntity(clinicToDto.convertList(clinics), HttpStatus.OK);
        }
        return new ResponseEntity(clinicToDto.convertList(clinics), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity setRate(Long id, Authentication authentication, ClinicRating clinicRating) {
        User user = userService.getLoggedIn(authentication);
        Patient patient = patientService.getPatientById(user.getId()).orElse(null);

        AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
        patient.setLbo(encription.encrypt());

        clinicRating.setPatient(patient);
        return new ResponseEntity(clinicToDto.convert(clinicService.rate(id, clinicRating)), HttpStatus.OK);
    }


}
