package com.example.Clinic.rest;

import com.example.Clinic.model.Patient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface PatientApi {

    @PostMapping(value = "/registration",
             consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Patient> registerUser(@Valid @RequestBody Patient patient);

    @GetMapping(value = "/allPatients",
        produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllPatients();

    @GetMapping(value = "/patient/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatient(@PathVariable("id") Long id);

    @PutMapping(value = "/patient/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient, @PathVariable("id") Long id);
}
