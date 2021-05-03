package com.example.Clinic.rest;

import com.example.Clinic.model.Clinic;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface ClinicApi {

    @GetMapping(value = "/allClinics",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllClinics();

    @GetMapping(value = "/clinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinic(@PathVariable("id") Long id);

    @GetMapping(value = "/clinics/{date}",
                produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinicsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);


    @PutMapping(value="/clinic", produces = {MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity update( @Valid @RequestBody Clinic clinic);
}
