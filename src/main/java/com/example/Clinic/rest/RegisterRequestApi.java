package com.example.Clinic.rest;

import com.example.Clinic.model.RegisterRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface RegisterRequestApi {

    @GetMapping(value = "/allRegisteringRequests",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRequests();

    @PostMapping(value = "/updateRequest",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<RegisterRequest> updateRequest(@RequestBody RegisterRequest request);

}
