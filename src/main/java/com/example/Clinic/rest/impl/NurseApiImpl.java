package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.NurseApi;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NurseApiImpl implements NurseApi {

    @Autowired
    private NurseService nurseService;

    @Override
    public ResponseEntity getAllNurses() {
        return new ResponseEntity(nurseService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNurse(Long id) {
        return new ResponseEntity(nurseService.findById(id), HttpStatus.OK);
    }
}
