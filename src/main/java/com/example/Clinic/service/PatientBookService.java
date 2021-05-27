package com.example.Clinic.service;

import com.example.Clinic.model.PatientBook;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Optional;

@Service
public interface PatientBookService {
    boolean addPatientBook(PatientBook patient) throws IOException, SAXException, ParserConfigurationException;
    PatientBook findById(Long id) throws IOException, SAXException, ParserConfigurationException;
    boolean updatePatientBook(PatientBook patient, Long id) throws IOException, SAXException, ParserConfigurationException;
}
