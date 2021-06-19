package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.rest.support.converter.AppointmentToDto;
import com.example.Clinic.rest.support.converter.DoctorToDto;
import com.example.Clinic.rest.support.converter.DtoToAppointment;
import com.example.Clinic.rest.support.converter.NurseToDto;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import com.example.Clinic.service.*;
import com.example.Clinic.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserService userService;

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

    @Override
    public ResponseEntity bookAppointment(Authentication authentication, long id) {
        User user = userService.getLoggedIn(authentication);
        Appointment appointmentForUpdate = appointmentService.findById(id);


        if(!appointmentForUpdate.getStatus().equals(AppointmentStatus.FREE)){
            return new ResponseEntity("Appointment is already reserved",HttpStatus.BAD_REQUEST);

        }else {
            appointmentForUpdate.setPatient(patientService.getPatientById(user.getId()).orElse(null));
            appointmentForUpdate.setStatus(AppointmentStatus.RESERVED);
            appointmentService.update(appointmentForUpdate);

            emailService.sendEmail(user.getEmail(), "Booking appointment report",
                    "Hello " + user.getName() + ", You have successfully reserved an appointment for: "
                            + appointmentForUpdate.getDate() + ", at: " + appointmentForUpdate.getStart()
            + " which be held by doctor: " + appointmentForUpdate.getDoctor().getUser().getName() + " " +
                            appointmentForUpdate.getDoctor().getUser().getLastName());
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
