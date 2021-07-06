package com.example.Clinic.rest;

import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.DrugChangeDto;
import com.example.Clinic.rest.support.dto.IllnessChangeDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/patientBooks")
public interface PatientBookApi {

    @PreAuthorize("hasAnyAuthority( 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientBook> addPatientBook(@Valid @RequestBody PatientBook patientBook) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAnyAuthority( 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientBook(@PathVariable("id") Long id) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAnyAuthority('PATIENT', 'DOCTOR')")
    @GetMapping(value = "/patient/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientBookByPatient(@PathVariable("id") Long id) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping(value = "/patient/healthCard",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientBookByLoggedPatient(Authentication authentication) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping(value = "/illnesses/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updatePatientBookIllnesses(@PathVariable("id") Long id, @Valid @RequestBody IllnessChangeDto dto) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping(value = "/drugs/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updatePatientBookDrugs(@PathVariable("id") Long id, @Valid @RequestBody DrugChangeDto dto) throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientBook> updatePatientBook(@Valid @RequestBody PatientBook patientBook, @PathVariable("id") Long id)
            throws ParserConfigurationException, SAXException, IOException;



}
