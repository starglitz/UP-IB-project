package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.ClinicApi;
import com.example.Clinic.rest.support.converter.ClinicToDto;
import com.example.Clinic.rest.support.dto.ClinicDto;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
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
    private ClinicToDto clinicToDto;

    @Autowired
    private UserService userService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Override
    public ResponseEntity getAllClinics() {
        return new ResponseEntity(clinicService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getClinic(Long id) {
        Clinic clinic = clinicService.findById(id).get();
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
    public ResponseEntity update(Long id, @Valid Clinic clinic) {
        return  new ResponseEntity(clinicService.update(clinic), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getClinicByLoggedInAdmin(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        ClinicAdmin admin = clinicAdminService.findById(user.getId());
        System.out.println(user);
        //System.out.println(admin);
        //System.out.println(admin.getClinic().getClinic_id());

//        Clinic clinic = clinicService.findById(admin.getClinic().getClinic_id()).orElse(null);
//        if(clinic == null) {
//            return new ResponseEntity("no such clinic", HttpStatus.NOT_FOUND);
//        }
        System.out.println(admin.getClinic());
//        Clinic clinic = clinicService.findById(admin.getClinic());
        return new ResponseEntity(clinicToDto.convert(admin.getClinic()), HttpStatus.OK);
    }


}
