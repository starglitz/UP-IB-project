package com.example.Clinic.rest;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Patient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface AppointmentApi {

    @PostMapping(value = "/addAppointment",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment);

    @GetMapping(value = "/allAppointments",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllAppointments();

    @GetMapping(value = "/appointment/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAppointment(@PathVariable("id") Long id);

    @GetMapping(value = "/clinicAppointments/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getClinicAppointments(@PathVariable long id);


    @GetMapping(value = "/appointments/free/clinic/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getFreeClinicAppointments(@PathVariable long id);

    @GetMapping(value = "/doctorAppointments/{id}",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getFreeDoctorAppointemnts(@PathVariable("id") Long id);


    @PutMapping(value = "/updateAppointment/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable("id") Long id);

    @DeleteMapping(value = "/deleteAppointment",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Appointment> deleteAppointment( @RequestBody Appointment appointment);

}
