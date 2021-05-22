package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.enumerations.RequestStatus;
import com.example.Clinic.rest.PatientApi;
import com.example.Clinic.rest.support.converter.DtoToPatient;
import com.example.Clinic.rest.support.converter.RegisterDtoToPatient;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.PatientRegisterDto;
import com.example.Clinic.service.PatientBookService;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class PatientApiImpl implements PatientApi {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private PatientBookService patientBookService;

    @Autowired
    private RegisterDtoToPatient registerDtoToPatient;

    @Override
    public ResponseEntity registerUser(@RequestBody @Valid PatientRegisterDto patientDto) {
        System.out.println(patientDto);
        Patient patient = registerDtoToPatient.convert(patientDto);

        Patient patientJpa = patientService.addPatient(patient);


        RegisterRequest request = new RegisterRequest(patientJpa, RequestStatus.PENDING, false);
        PatientBook book = new PatientBook();
        book.setPatient(patientJpa);
        patientBookService.addPatientBook(book);
        registerRequestService.addRegisterRequest(request);

        patientJpa.setPatientBookId(book.getId());
        patientService.updatePatient(patientJpa, patientJpa.getId());
        System.out.println(patientJpa);
        return new ResponseEntity<>(patient, HttpStatus.OK);

    }



    @Override
    public ResponseEntity getAllPatients() {
        List<Patient> patients = patientService.getAll();
        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPatient(Long id) {

        Optional<Patient> patient = patientService.getPatientById(id);
        return  new ResponseEntity(patient, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(Long id,@Valid Patient patient) {
        Patient patientJpa = patientService.updatePatient(patient, id);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }


//    public void initializeTestData() {
//        Patient patient1= new Patient("email@gmail.com", "pass123", "Clark", "Johnson",
//                "Address Street 16a", "Novi Sad", "Serbia", "012345678", "095540");
//
//        Patient patient2= new Patient("email2@yahoo.com", "654321", "Eve", "Hamilton",
//                "Address Street 2", "Belgrade", "Serbia", "098765432", "196547");
//        patientService.addPatient(patient1);
//        patientService.addPatient(patient2);
//
//    }



}
