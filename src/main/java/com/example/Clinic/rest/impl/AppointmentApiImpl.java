package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.service.AppointmentService;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Component
public class AppointmentApiImpl implements AppointmentApi {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;

    @Override
    public ResponseEntity getAllAppointments() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + appointmentService.findAll());
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
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment) {
        Doctor doctor = doctorService.findById(appointment.getDoctor().getId()).get();
        appointment.setDoctor(doctor);
        Nurse nurse = nurseService.findById(appointment.getNurse().getId()).get();
        appointment.setNurse(nurse);

        return new ResponseEntity(appointmentService.add(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(@Valid Appointment appointment) {
        Doctor doctor = doctorService.findById(appointment.getDoctor().getId()).get();
        appointment.setDoctor(doctor);
        Nurse nurse = nurseService.findById(appointment.getNurse().getId()).get();
        appointment.setNurse(nurse);

        return new ResponseEntity(appointmentService.update(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> deleteAppointment(Appointment appointment) {
        return new ResponseEntity(appointmentService.delete(appointment), HttpStatus.OK);
    }
}
