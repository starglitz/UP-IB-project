package com.example.Clinic.rest;

import com.example.Clinic.model.PatientBook;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //@PreAuthorize("hasAnyAuthority( 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientBook(@PathVariable("id") Long id) throws ParserConfigurationException, SAXException, IOException;

 //   @PreAuthorize("hasAnyAuthority('DOCTOR', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientBook> updatePatientBook(@Valid @RequestBody PatientBook patientBook, @PathVariable("id") Long id) throws ParserConfigurationException, SAXException, IOException;

}
