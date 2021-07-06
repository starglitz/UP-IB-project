package com.example.Clinic.repository;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT * FROM patient WHERE id in (SELECT patient_id from appointment WHERE doctor_id = ?1)",
            nativeQuery = true)
    List<Patient> findByDoctorId(Long doctor_id);

}
