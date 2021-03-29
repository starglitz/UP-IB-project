package com.example.Clinic.rest;

import com.example.Clinic.model.Patient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface PatientApi {

    @PostMapping(value = "/registration",
             consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity registerUser(@RequestBody Patient patient);

    @GetMapping(value = "/allPatients",
        produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllPatients();

    @GetMapping(value = "/patient/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatient(@PathVariable("id") Long id);
}