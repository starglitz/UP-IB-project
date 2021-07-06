package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.example.Clinic.rest.AppointmentApi;
import com.example.Clinic.rest.support.converter.AppointmentToDto;
import com.example.Clinic.rest.support.converter.DoctorToDto;
import com.example.Clinic.rest.support.converter.DtoToAppointment;
import com.example.Clinic.rest.support.converter.NurseToDto;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import com.example.Clinic.security.services.AsymmetricEncription;
import com.example.Clinic.service.*;
import com.example.Clinic.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentApiImpl implements AppointmentApi {

    @Autowired
    private AppointmentService appointmentService;


    @Autowired
    private PatientService patientService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientBookService patientBookService;

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
    public ResponseEntity getNumberByMonths(Long id) {
        return new ResponseEntity(appointmentService.findNumberByMonts(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDailyNumberByMonths(Long id,String month) {
        return new ResponseEntity(appointmentService.findNumberByDaysInMonth(id,month), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNumberByWeeks(Long id) {
        return new ResponseEntity(appointmentService.findNumberByWeeks(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getIncomeBetweenDates(Long id, LocalDate dateFrom, LocalDate dateTo) {
        return new ResponseEntity(appointmentService.findIncomeBetweenDates(id, dateFrom, dateTo), HttpStatus.OK);
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
    public ResponseEntity finishAppointment(@Valid AppointmentDto dto) throws ParserConfigurationException, SAXException, IOException {

        Appointment appointment = dtoToAppointment.convert(dto);
        appointmentService.update(appointment);

        assert appointment != null;
        PatientBook patientBook = patientBookService.findById(appointment.getPatient().getPatientBookId());

        if (patientBook != null) {
            System.out.println("PATIENT BOOK: " + patientBook.getIllnessHistory());
            System.out.println("CONCLUSION: " + appointment.getConclusion());
            List<String> illnesses =  new ArrayList<String>((patientBook.getIllnessHistory()));
            String conclusion = appointment.getConclusion();
            illnesses.add(conclusion);
            patientBook.setIllnessHistory(illnesses);
            patientBookService.updatePatientBook(patientBook, patientBook.getId());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        return new ResponseEntity<>("Patient book doesnt exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getPatientAppointments(long id) {
        List<AppointmentDto> appointments = appointmentToDto.convertList(appointmentService.findByPatient(id));
        return new ResponseEntity(appointments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> getPatientFinishedAppointments(long id) {
        List<AppointmentDto> appointments = appointmentToDto.convertList(appointmentService.findByPatient(id));
        return new ResponseEntity(appointments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> getPatientAppointmentsHistory(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);

        List<AppointmentDto> appointments = appointmentToDto.convertList(appointmentService.findByPatientFinished(user.getId()));
        return new ResponseEntity(appointments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        System.out.println("!!!!");
        System.out.println(appointmentDto);

        Appointment appointment = dtoToAppointment.convert(appointmentDto);

        if(appointmentService.add(appointment)) {
            return new ResponseEntity("added", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("bad request :( ", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateAppointment(@Valid AppointmentDto dto, Long id) {

        Appointment appointment = dtoToAppointment.convert(dto);
        appointmentService.update(appointment);

        return new ResponseEntity<>(dto, HttpStatus.OK);
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
            System.out.println("ITS CALLED!!!");
            Patient patient = patientService.getPatientById(user.getId()).orElse(null);

            AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
            patient.setLbo(encription.encrypt());


            appointmentForUpdate.setPatient(patient);
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

    @Override
    public ResponseEntity<Appointment> getNurseAppointments(long id) {
        Nurse nurse = nurseService.findById(id);
        if (nurse != null) {
            List<AppointmentDto> appointments = appointmentToDto.convertList(appointmentService.findByNurse(nurse));
            return new ResponseEntity(appointments, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Appointment> getDoctorAppointments(long id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor != null) {
            List<AppointmentDto> appointments = appointmentToDto.convertList(appointmentService.findByDoctor(doctor));
            return new ResponseEntity(appointments, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
