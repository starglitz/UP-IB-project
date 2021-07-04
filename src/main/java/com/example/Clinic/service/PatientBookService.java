package com.example.Clinic.service;

import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.DrugChangeDto;
import com.example.Clinic.rest.support.dto.IllnessChangeDto;
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
    boolean updatePatientBookIllness(PatientBook patientBook, IllnessChangeDto dto) throws IOException, SAXException, ParserConfigurationException;
    boolean updatePatientBookDrugs(PatientBook patientBook, DrugChangeDto dto) throws IOException, SAXException, ParserConfigurationException;
}
