package com.example.Clinic.service;

import com.example.Clinic.model.PatientBook;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PatientBookService {
    boolean addPatientBook(PatientBook patient);
    Optional<PatientBook> findById(Long id);
    boolean updatePatientBook(PatientBook patient, Long id);
}
