package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AppointmentApiImpl implements AppointmentApi {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public ResponseEntity getAllAppointments() {
        return new ResponseEntity(appointmentService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAppointment(Long id) {
        if(appointmentService.findById(id) != null) {
            return new ResponseEntity(appointmentService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity("No such appointment", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Appointment> addAppointment(@Valid Appointment appointment) {
        return new ResponseEntity(appointmentService.add(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(@Valid Appointment appointment) {
        return new ResponseEntity(appointmentService.update(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> deleteAppointment(@Valid Appointment appointment) {
        return new ResponseEntity(appointmentService.delete(appointment), HttpStatus.OK);
    }
}
