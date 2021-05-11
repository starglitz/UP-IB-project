package com.example.Clinic.repository;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.PatientBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientBookRepository extends JpaRepository<PatientBook, Long> {

    //(value = "SELECT * FROM patient_book WHERE id = ?1",
      //      nativeQuery = true)
    //Optional<PatientBook> findByPatientBookId(Long id);
}
