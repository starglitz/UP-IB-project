package com.example.Clinic.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface NurseApi {

    @GetMapping(value = "/allNurses",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllNurses();

    @GetMapping(value = "/nurse/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNurse(@PathVariable("id") Long id);
}
