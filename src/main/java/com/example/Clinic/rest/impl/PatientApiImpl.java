package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.RequestStatus;
import com.example.Clinic.rest.PatientApi;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @Override
    public ResponseEntity<Patient> registerUser(@RequestBody @Valid Patient patient) {
        boolean valid = patientService.addPatient(patient);
        if(valid) {
            RegisterRequest request = new RegisterRequest(patient, RequestStatus.PENDING, false);
            registerRequestService.addRegisterRequest(request);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(patient, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<Patient> updatePatient(@Valid Patient patient, Long id) {
        boolean valid = patientService.updatePatient(patient, id);
        if(valid){
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(patient, HttpStatus.BAD_REQUEST);
    }


    public void initializeTestData() {
        Patient patient1= new Patient("email@gmail.com", "pass123", "Clark", "Johnson",
                "Address Street 16a", "Novi Sad", "Serbia", "012345678", "095540");

        Patient patient2= new Patient("email2@yahoo.com", "654321", "Eve", "Hamilton",
                "Address Street 2", "Belgrade", "Serbia", "098765432", "196547");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);

    }



}
