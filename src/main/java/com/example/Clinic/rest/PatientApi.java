package com.example.Clinic.rest;

import com.example.Clinic.model.Patient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface PatientApi {

    @PostMapping(value = "/registration",
             consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity registerUser(@RequestBody Patient patient);

    @GetMapping(value = "/allPatients",
        produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllPatients();
}
