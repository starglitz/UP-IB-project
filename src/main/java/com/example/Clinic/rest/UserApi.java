package com.example.Clinic.rest;

import com.example.Clinic.model.LoginForm;
import com.example.Clinic.rest.support.dto.RegisterNurseDto;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public interface UserApi {

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTER_ADMIN', 'PATIENT', 'NURSE', 'DOCTOR')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<UserRegisterDto> update(@PathVariable("id") Long id, @Valid @RequestBody UserRegisterDto user);

    @PermitAll
    @GetMapping(value = "/profile",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getLoggedIn(Authentication authentication);

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAll();

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getUser(@PathVariable("id") Long id);




}
