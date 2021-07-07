package com.example.Clinic.rest;

import com.example.Clinic.model.LoginForm;
import com.example.Clinic.rest.support.dto.RegisterNurseDto;
import com.example.Clinic.rest.support.dto.UserDto;
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

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN', 'PATIENT', 'NURSE', 'DOCTOR')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<UserRegisterDto> update(@PathVariable("id") Long id, @Valid @RequestBody UserRegisterDto user);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN', 'NURSE', 'DOCTOR')")
    @GetMapping(value = "/profile",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getLoggedIn(Authentication authentication);

    @PreAuthorize("hasAnyAuthority('PATIENT', 'NURSE', 'DOCTOR')")
    @GetMapping(value = "email/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getLoggedInUserId(@PathVariable("email") String email);

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAll();

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getUser(@PathVariable("id") Long id);


    @PutMapping(value = "/enable/{token}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity enable(@PathVariable("token") String token);


    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN', 'PATIENT', 'NURSE', 'DOCTOR')")
    @PutMapping(value = "/passwordUpdate/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<UserRegisterDto> changePassword(@PathVariable("id") Long id, @Valid @RequestBody UserRegisterDto user);


    @PermitAll
    @GetMapping(value = "/firstTime",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity firstTime(Authentication authentication);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN', 'PATIENT', 'NURSE', 'DOCTOR')")
    @PutMapping(value = "/profile/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<UserDto> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody UserDto user);


}
