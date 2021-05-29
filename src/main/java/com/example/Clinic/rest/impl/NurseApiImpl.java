package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.NurseApi;
import com.example.Clinic.rest.support.converter.DtoToNurse;
import com.example.Clinic.rest.support.converter.NurseToDto;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.RegisterNurseDto;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class NurseApiImpl implements NurseApi {

    @Autowired
    private NurseService nurseService;


    @Autowired
    private NurseToDto nurseToDto;

    @Override
    public ResponseEntity getAllNurses() {
        return new ResponseEntity(nurseService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNurse(Long id) {
        return new ResponseEntity(nurseService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegisterNurseDto> create(@Valid RegisterNurseDto nurseDto) {

        //String email, String password, String name, String lastName,
        //                String address, String city, String country, String phoneNumber
        User user = new User(nurseDto.getUser().getEmail(), nurseDto.getUser().getPassword(),
                nurseDto.getUser().getName(), nurseDto.getUser().getLastName(),
                nurseDto.getUser().getAddress(), nurseDto.getUser().getCity(),
                nurseDto.getUser().getCountry(), nurseDto.getUser().getPhoneNumber(),
                nurseDto.getUser().isEnabled());



        Nurse nurse = new Nurse(user);
        Nurse created = nurseService.create(nurse);

        return new ResponseEntity("Successfully created", HttpStatus.OK);
    }
}
