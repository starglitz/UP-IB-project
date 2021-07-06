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
    @GetMapping(value = "/count/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNumberByMonths(@PathVariable("id") Long id);

    @PermitAll
    @GetMapping(value = "/count/days/{id}/{month}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getDailyNumberByMonths(@PathVariable("id") Long id,@PathVariable("month") String month);

    @PermitAll
    @GetMapping(value = "/count/weeks/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNumberByWeeks(@PathVariable("id") Long id);

    @PermitAll
    @GetMapping(value = "/income/{id}/{dateFrom}/{dateTo}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getIncomeBetweenDates(@PathVariable("id") Long id, @PathVariable("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 LocalDate dateFrom,
                                         @PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 LocalDate dateTo);

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

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value = "/patient/finished/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> getPatientFinishedAppointments(@PathVariable long id);

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping(value = "/appointmentHistory", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> getPatientAppointmentsHistory(Authentication authentication);

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

    @PreAuthorize("hasAuthority('NURSE')")
    @GetMapping(value = "/nurse/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> getNurseAppointments(@PathVariable long id);

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value = "/doctor/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> getDoctorAppointments(@PathVariable long id);

}
