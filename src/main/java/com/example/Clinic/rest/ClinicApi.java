package com.example.Clinic.rest;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicRating;
import com.example.Clinic.rest.support.dto.ClinicDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/clinics")
public interface ClinicApi {

    @PermitAll
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllClinics();

    @PermitAll
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinic(@PathVariable("id") Long id);

    @PermitAll
    @GetMapping(value = "/date/{date}",
                produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinicsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);


    @PreAuthorize("hasAnyAuthority('CLINIC_CENTRE_ADMIN')")
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity create(@Valid @RequestBody ClinicDto dto);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity update(@PathVariable("id") Long id,@Valid @RequestBody Clinic clinic);

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinicByLoggedInAdmin(Authentication authentication);

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping(value = "/not_rated", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNotRatedVisitedClinics(Authentication authentication);

    @PreAuthorize("hasAuthority('PATIENT')")
    @PutMapping(value = "/rate/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity setRate(@PathVariable("id") Long id, Authentication authentication,@RequestBody ClinicRating rating);



}
