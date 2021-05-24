package com.example.Clinic.rest;

import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/registerRequests")
public interface RegisterRequestApi {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRequests();

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<RegisterRequest> updateRequest(@PathParam("id") Long id, @RequestBody RegisterRequestDto request);

}
