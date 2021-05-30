package com.example.Clinic.rest;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.AppointmentDto;
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
@RequestMapping("/appointments")
public interface AppointmentApi {


    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointment);

    @PermitAll
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllAppointments();

    @PermitAll
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAppointment(@PathVariable("id") Long id);

    @PermitAll
    @GetMapping(value = "/clinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinicAppointments(@PathVariable long id);


    @PermitAll
    @GetMapping(value = "/free/clinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getFreeClinicAppointments(@PathVariable long id);

    @PermitAll
    @GetMapping(value = "/free/doctor/{id}/date/{date}",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getFreeDoctorAppointemntsByDate(@PathVariable("id") Long id, @PathVariable("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate date);


    @PreAuthorize("hasAnyAuthority('PATIENT', 'DOCTOR', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody AppointmentDto appointment, @PathVariable("id") Long id);


    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") Long id);
}
