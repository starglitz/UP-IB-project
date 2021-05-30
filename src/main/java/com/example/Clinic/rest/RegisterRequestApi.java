package com.example.Clinic.rest;

import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/registerRequests")
public interface RegisterRequestApi {

    @PreAuthorize("hasAuthority('CLINIC_CENTRE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRequests();

    @PreAuthorize("hasAuthority('CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<RegisterRequest> updateRequest(@PathParam("id") Long id, @RequestBody RegisterRequestDto request);

}
