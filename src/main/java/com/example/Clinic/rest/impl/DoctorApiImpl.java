package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.rest.DoctorApi;
import com.example.Clinic.rest.support.converter.DoctorToDto;
import com.example.Clinic.rest.support.converter.DtoToDoctor;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.rest.support.dto.RegisterDoctorDto;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.security.services.AsymmetricEncription;
import com.example.Clinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorApiImpl implements DoctorApi {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorToDto doctorToDto;

    @Autowired
    private DtoToDoctor dtoToDoctor;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Override
    public ResponseEntity getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctor(Long id) {
        Doctor doctor = doctorService.findById(id);
        if(doctor == null) {
            return new ResponseEntity("Doctor with id " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        DoctorDto dto = doctorToDto.convert(doctor);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctorsByClinicAndDate(Long id, LocalDate date) {
        List<Doctor> doctors = doctorService.findByClinicAndDate(id,date);
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getDoctorByClinicId(Long id) {
        List<Doctor> doctors = doctorService.findByClinicId(id);
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegisterDoctorDto> create(@Valid RegisterDoctorDto doctor, Authentication authentication) {
        User user = new User(doctor.getUser().getEmail(), doctor.getUser().getPassword(),
                doctor.getUser().getName(), doctor.getUser().getLastName(),
                doctor.getUser().getAddress(),doctor.getUser().getCity(),
                doctor.getUser().getCountry(), doctor.getUser().getPhoneNumber(),
                doctor.getUser().isEnabled());

       // Clinic clinic = clinicService.findById(doctor.getClinic().getClinic_id());
        User loggedInAdmin = userService.getLoggedIn(authentication);



        Doctor doctor1 = new Doctor(user);
        doctor.setGrade(0);
//        if(clinic != null) {
//            doctor1.setClinic(clinic);
//        }
        boolean ok = doctorService.create(doctor1, authentication);

        if(ok) {
            return new ResponseEntity("Success", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Noo :(", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getNotRatedByPatient(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        List<Doctor> doctors = doctorService.getNotRatedByPatientId(user.getId());
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity rate(Long id, Authentication authentication, DoctorRating doctorRating) {
        User user = userService.getLoggedIn(authentication);
        Patient patient = patientService.getPatientById(user.getId()).orElse(null);

        AsymmetricEncription encription = new AsymmetricEncription(patient.getLbo());
        patient.setLbo(encription.encrypt());

        doctorRating.setPatient(patient);
        return new ResponseEntity(doctorToDto.convert(doctorService.rate(id, doctorRating)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getByAdminsClinic(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        ClinicAdmin clinicAdmin = clinicAdminService.findById(user.getId());
        List<Doctor> doctors = doctorService.findByClinicId(clinicAdmin.getClinic().getClinic_id());
        List<DoctorDto> dtos = new ArrayList<>();
        for(Doctor doctor : doctors) {
            DoctorDto dto = doctorToDto.convert(doctor);
            dtos.add(dto);
        }
        return new ResponseEntity(dtos, HttpStatus.OK);
    }
}
