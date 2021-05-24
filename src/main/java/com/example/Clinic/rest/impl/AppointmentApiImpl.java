package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.rest.support.converter.AppointmentToDto;
import com.example.Clinic.rest.support.converter.DoctorToDto;
import com.example.Clinic.rest.support.converter.DtoToAppointment;
import com.example.Clinic.rest.support.converter.NurseToDto;
import com.example.Clinic.rest.support.dto.AppointmentDto;
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
import java.time.LocalDate;
import java.util.List;
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

    @Autowired
    private DoctorToDto doctorToDto;

    @Autowired
    private NurseToDto nurseToDto;

    @Autowired
    private DtoToAppointment dtoToAppointment;

    @Autowired
    private AppointmentToDto appointmentToDto;

    @Override
    public ResponseEntity getAllAppointments() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + appointmentService.findAll());
        return new ResponseEntity(appointmentService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAppointment(Long id) {
        Appointment appointment = appointmentService.findById(id);
        if(appointment != null) {
            return new ResponseEntity(appointmentToDto.convert(appointment), HttpStatus.OK);
        }
        return new ResponseEntity("No such appointment", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getClinicAppointments(long id) {
        List<Appointment> appointments = appointmentService.findByClinicId(id);
        return new ResponseEntity(appointmentToDto.convertList(appointments), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFreeClinicAppointments(long id) {
        List<Appointment> appointments = appointmentService.findFreeByClinicId(id);
        return new ResponseEntity(appointmentToDto.convertList(appointments), HttpStatus.OK);
    }
    @Override
    public ResponseEntity getFreeDoctorAppointemntsByDate(Long doctorId, LocalDate date) {
        List<Appointment> appointments = appointmentService.findFreeByDoctorAndDate(doctorId, date);
        return new ResponseEntity(appointmentToDto.convertList(appointments), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        System.out.println("!!!!");
        System.out.println(appointmentDto);

        Appointment appointment = dtoToAppointment.convert(appointmentDto);

        return new ResponseEntity(appointmentService.add(appointment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateAppointment(@Valid AppointmentDto appointmentParam, Long id) {

        Appointment appointment = dtoToAppointment.convert(appointmentParam);

        boolean valid = appointmentService.update(appointment);

        return new ResponseEntity(appointmentParam, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> deleteAppointment(Long id) {
        Appointment appointment = appointmentService.findById(id);
        return new ResponseEntity(appointmentService.delete(appointment), HttpStatus.OK);
    }
}
