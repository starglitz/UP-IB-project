package com.example.Clinic.rest;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping(value = "/finish",
            consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> finishAppointment(@Valid @RequestBody AppointmentDto appointment)
            throws ParserConfigurationException, SAXException, IOException;

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value = "/patient/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> getPatientAppointments(@PathVariable long id);

    @PreAuthorize("hasAnyAuthority('PATIENT', 'DOCTOR', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody AppointmentDto appointment, @PathVariable("id") Long id);

    @PreAuthorize("hasAnyAuthority('CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") Long id);

    @PermitAll
    @PostMapping(value = "/booking/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity bookAppointment(Authentication authentication, @PathVariable long id);

}
