package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.service.AppointmentService;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.NurseService;
import com.example.Clinic.service.PatientService;
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

    @Autowired
    private PatientService patientService;

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
    public ResponseEntity getClinicAppointments(long id) {
        return new ResponseEntity(appointmentService.findByClinicId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFreeClinicAppointments(long id) {
        return new ResponseEntity(appointmentService.findFreeByClinicId(id), HttpStatus.OK);
    }
    @Override
    public ResponseEntity getFreeDoctorAppointemnts(Long doctorId) {
        return new ResponseEntity(appointmentService.findFreeByDoctor(doctorId), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment) {
        return new ResponseEntity(appointmentService.add(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(@Valid Appointment appointmentParam, Long id) {
        boolean valid = appointmentService.update(appointmentParam);
        Appointment appointment = appointmentService.findById(id);
        if (valid) {
            Doctor doctor = doctorService.findById(appointmentParam.getDoctor().getId()).get();
            appointment.setDoctor(doctor);
            Nurse nurse = nurseService.findById(appointmentParam.getNurse().getId()).get();
            appointment.setNurse(nurse);
            Patient patient = patientService.getPatientById(appointmentParam.getPatient().getId()).get();
            appointment.setPatient(patient);

            appointment.setStart(appointmentParam.getStart());
            appointment.setEnd(appointmentParam.getEnd());
            appointment.setStatus(appointmentParam.getStatus());
            appointment.setDeleted(appointmentParam.isDeleted());
            appointment.setPrice(appointmentParam.getPrice());

            return new ResponseEntity(appointmentService.update(appointment), HttpStatus.OK);
        }
        return new ResponseEntity<>(appointment, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Appointment> deleteAppointment(Long id) {
        Appointment appointment = appointmentService.findById(id);
        return new ResponseEntity(appointmentService.delete(appointment), HttpStatus.OK);
    }
}
