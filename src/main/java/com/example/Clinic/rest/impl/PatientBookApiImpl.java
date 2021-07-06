package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.PatientBookApi;
import com.example.Clinic.rest.support.converter.PatientToDto;
import com.example.Clinic.rest.support.dto.DrugChangeDto;
import com.example.Clinic.rest.support.dto.IllnessChangeDto;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.service.PatientBookService;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PatientBookApiImpl implements PatientBookApi {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientBookService patientBookService;

    @Autowired
    private PatientToDto patientToDto;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<PatientBook> addPatientBook(@Valid PatientBook patientBook) throws ParserConfigurationException, SAXException, IOException {
        boolean valid = patientBookService.addPatientBook(patientBook);

        if (valid)
            return new ResponseEntity(patientBook, HttpStatus.OK);

        return new ResponseEntity(patientBook, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getPatientBook(Long id) throws ParserConfigurationException, SAXException, IOException {
        PatientBook patientBook = patientBookService.findById(id);
        System.out.println(patientBook);

        //Long id, PatientDto patientDto, List<String> illnessHistory,
        //                          List<String> drugs, String xml
        PatientDto patient = patientToDto.convert(patientBook.getPatient());
        PatientBookDto dto = new PatientBookDto(patientBook.getId(), patient, patientBook.getIllnessHistory(),
                patientBook.getDrugs(), patientBook.getXml());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPatientBookByPatient(Long id) throws ParserConfigurationException, SAXException, IOException {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            PatientBook patientBook = patientBookService.findById(patient.get().getPatientBookId());
            System.out.println(patientBook);
            PatientDto patientDto = patientToDto.convert(patientBook.getPatient());
            PatientBookDto dto = new PatientBookDto(patientBook.getId(), patientDto, patientBook.getIllnessHistory(),
                    patientBook.getDrugs(), patientBook.getXml());
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getPatientBookByLoggedPatient(Authentication authentication) throws ParserConfigurationException, SAXException, IOException {
        User user = userService.getLoggedIn(authentication);
        Optional<Patient> patient = patientService.getPatientById(user.getId());
        if (patient.isPresent()) {
            PatientBook patientBook = patientBookService.findById(patient.get().getPatientBookId());
            System.out.println(patientBook);
            PatientDto patientDto = patientToDto.convert(patientBook.getPatient());
            PatientBookDto dto = new PatientBookDto(patientBook.getId(), patientDto, patientBook.getIllnessHistory(),
                    patientBook.getDrugs(), patientBook.getXml());
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity updatePatientBookIllnesses(Long id, IllnessChangeDto dto) throws ParserConfigurationException, SAXException, IOException {
        PatientBook patientBook = patientBookService.findById(id);
        if (patientBook != null) {
            boolean valid = patientBookService.updatePatientBookIllness(patientBook, dto);
            if(valid) {
                return new ResponseEntity<>("Patient book successfully updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("You cannot edit this illness !", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity updatePatientBookDrugs(Long id, DrugChangeDto dto) throws ParserConfigurationException, SAXException, IOException {
        PatientBook patientBook = patientBookService.findById(id);
        if (patientBook != null) {
            boolean valid = patientBookService.updatePatientBookDrugs(patientBook, dto);
            if(valid) {
                return new ResponseEntity<>("Patient book successfully updated", HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<PatientBook> updatePatientBook(@Valid PatientBook patientBook, Long id) throws ParserConfigurationException, SAXException, IOException {
        boolean valid = patientBookService.updatePatientBook(patientBook, id);

        if (valid)
            return new ResponseEntity(patientBook, HttpStatus.OK);

        return new ResponseEntity(patientBook, HttpStatus.BAD_REQUEST);
    }
}
