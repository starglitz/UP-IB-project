package com.example.Clinic.rest.impl;

import com.example.Clinic.rest.RegisterRequestApi;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestApiImpl implements RegisterRequestApi {


    @Autowired
    private RegisterRequestService registerRequestService;

    @Override
    public ResponseEntity getAllRequests() {
        return new ResponseEntity(registerRequestService.getAll(), HttpStatus.OK);
    }
}
