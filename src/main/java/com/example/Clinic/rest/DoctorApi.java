package com.example.Clinic.rest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface DoctorApi {

    @GetMapping(value = "/allDoctors",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllDoctors();

    @GetMapping(value = "/doctor/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getDoctor(@PathVariable("id") Long id);

    @GetMapping(value = "/doctors/{id}/{date}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getDoctorsByClinicAndDate(@PathVariable("id") Long id,
                                             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                     LocalDate date);

    @GetMapping(value = "/doctorByClinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getDoctorByClinicId(@PathVariable("id") Long id);

}
