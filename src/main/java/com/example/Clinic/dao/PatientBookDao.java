package com.example.Clinic.dao;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientBookDao {
    PatientBook addPatientBook(PatientBook patient);
    Optional<PatientBook> findById(Long id);
    PatientBook updatePatientBook(PatientBook patient, Long id);
}
