package com.example.Clinic.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface RegisterRequestApi {

    @GetMapping(value = "/allRegisteringRequests",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRequests();

}
