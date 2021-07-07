package com.example.Clinic.rest;

import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.PatientRegisterDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/patients")
public interface PatientApi {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Patient> registerUser(@Valid @RequestBody PatientRegisterDto patient) throws ParserConfigurationException, SAXException, IOException;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllPatients();

    @PreAuthorize("hasAnyAuthority('PATIENT')")
    @GetMapping(value = "/patient",produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getLogged(Authentication authentication);

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatient(@PathVariable("id") Long id);

    @PreAuthorize("hasAnyAuthority('PATIENT', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientDto> updatePatient(@PathVariable("id") Long id ,@Valid @RequestBody PatientDto patientDto);

    @GetMapping(value = "/doctor",produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientsByDoctorId(Authentication authentication);
}
