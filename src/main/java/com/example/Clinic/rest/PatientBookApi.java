package com.example.Clinic.rest;

import com.example.Clinic.model.PatientBook;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/patientBooks")
public interface PatientBookApi {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientBook> addPatientBook(@Valid @RequestBody PatientBook patientBook);

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getPatientBook(@PathVariable("id") Long id);

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PatientBook> updatePatientBook(@Valid @RequestBody PatientBook patientBook, @PathVariable("id") Long id);

}
