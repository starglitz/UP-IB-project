package com.example.Clinic.rest;

import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.RegisterNurseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/nurses")
public interface NurseApi {

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllNurses();

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNurse(@PathVariable("id") Long id);

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<RegisterNurseDto> create(@RequestBody @Valid RegisterNurseDto nurse, Authentication authentication);

    @PreAuthorize("hasAuthority('CLINIC_ADMIN')")
    @GetMapping(value = "/admin/clinic",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getByAdminsClinic(Authentication authentication);

}
