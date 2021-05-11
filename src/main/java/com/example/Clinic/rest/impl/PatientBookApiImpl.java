package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.PatientBookApi;
import com.example.Clinic.service.PatientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Optional;

@Component
public class PatientBookApiImpl implements PatientBookApi {

    @Autowired
    private PatientBookService patientBookService;

    @Override
    public ResponseEntity<PatientBook> addPatientBook(@Valid PatientBook patientBook) {
        boolean valid = patientBookService.addPatientBook(patientBook);

        if (valid)
            return new ResponseEntity(patientBook, HttpStatus.OK);

        return new ResponseEntity(patientBook, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getPatientBook(Long id) {
        Optional<PatientBook> patientBook = patientBookService.findById(id);
        System.out.println(patientBook);
        return new ResponseEntity(patientBook, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientBook> updatePatientBook(@Valid PatientBook patientBook, Long id) {
        boolean valid = patientBookService.updatePatientBook(patientBook, id);

        if (valid)
            return new ResponseEntity(patientBook, HttpStatus.OK);

        return new ResponseEntity(patientBook, HttpStatus.BAD_REQUEST);
    }
}
