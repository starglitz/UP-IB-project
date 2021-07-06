package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.rest.NurseApi;
import com.example.Clinic.rest.support.converter.DtoToNurse;
import com.example.Clinic.rest.support.converter.NurseToDto;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.RegisterNurseDto;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
import com.example.Clinic.service.NurseService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class NurseApiImpl implements NurseApi {

    @Autowired
    private NurseService nurseService;


    @Autowired
    private NurseToDto nurseToDto;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getAllNurses() {
        return new ResponseEntity(nurseService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNurse(Long id) {
        return new ResponseEntity(nurseService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegisterNurseDto> create(@Valid RegisterNurseDto nurseDto, Authentication authentication) {

        //String email, String password, String name, String lastName,
        //                String address, String city, String country, String phoneNumber
        User user = new User(nurseDto.getUser().getEmail(), nurseDto.getUser().getPassword(),
                nurseDto.getUser().getName(), nurseDto.getUser().getLastName(),
                nurseDto.getUser().getAddress(), nurseDto.getUser().getCity(),
                nurseDto.getUser().getCountry(), nurseDto.getUser().getPhoneNumber(),
                nurseDto.getUser().isEnabled());

    //    Clinic clinic = clinicService.findById(nurseDto.getClinic().getClinic_id());


        Nurse nurse = new Nurse(user);
//        if(clinic != null) {
//            nurse.setClinic(clinic);
//        }
        boolean ok= nurseService.create(nurse, authentication);
        if(ok) {
            return new ResponseEntity("Successfully created", HttpStatus.OK);
        }
        else {
            return new ResponseEntity(":(", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity getByAdminsClinic(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        ClinicAdmin clinicAdmin = clinicAdminService.findById(user.getId());
        List<Nurse> nurses = nurseService.findByClinicId(clinicAdmin.getClinic().getClinic_id());
        List<NurseDto> dtos = new ArrayList<>();
        for(Nurse nurse : nurses) {
            NurseDto dto = nurseToDto.convert(nurse);
            dto.setId(nurse.getId());
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }
}
