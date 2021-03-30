package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.PatientApi;
import com.example.Clinic.service.PatientService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class PatientApiImpl implements PatientApi {

    @Autowired
    private PatientService patientService;


    @Override
    public ResponseEntity<String> registerUser(Patient patient) {
        patientService.addPatient(patient);

        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/registerTest")
    public ResponseEntity register(@RequestBody Patient patient) {
        System.out.println(patient);

        return new ResponseEntity(new Response(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/patients")
    public ResponseEntity getAllPatients() {

        List<Patient> patients = patientService.getALl();
        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPatient(Long id) {

        Optional<Patient> patient = patientService.getPatientById(id);
        return  new ResponseEntity(patient, HttpStatus.OK);
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
