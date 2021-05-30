package com.example.Clinic.rest;

import com.example.Clinic.rest.support.dto.RegisterClinicAdminDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/clinic/admin")
public interface ClinicAdminApi {
    @PreAuthorize("hasAnyAuthority('CLINIC_CENTRE_ADMIN')")
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity create(@Valid @RequestBody RegisterClinicAdminDto dto);
}
