package com.example.Clinic.rest;

import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.RegisterDoctorDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/doctors")
public interface DoctorApi {

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllDoctors();

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getDoctor(@PathVariable("id") Long id);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN','PATIENT')")
    @GetMapping(value = "/clinic/{clinic_id}/date/{date}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getDoctorsByClinicAndDate(@PathVariable("clinic_id") Long id,
                                             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                     LocalDate date);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @GetMapping(value = "/clinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getDoctorByClinicId(@PathVariable("id") Long id);

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegisterDoctorDto> create(@RequestBody @Valid RegisterDoctorDto doctor);

}
