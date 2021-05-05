package com.example.Clinic.repository;

import com.example.Clinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1)",
            nativeQuery = true)
    List<Appointment> findByClinicId(Long clinic_id);


    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1) and status='FREE'",
            nativeQuery = true)
    List<Appointment> findFreeByClinicId(Long clinic_id);

    @Query(value = "SELECT * FROM appointment WHERE doctor_id =?",
            nativeQuery = true)
    List<Appointment> findFreeByDoctor(Long doctor_id);


}
