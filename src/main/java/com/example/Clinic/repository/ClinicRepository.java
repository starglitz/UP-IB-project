package com.example.Clinic.repository;

import com.example.Clinic.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query(value = "SELECT * from  clinic where clinic_id in\n" +
            "(SELECT doctor.clinic_id from doctor where id in\n" +
            "                             (SELECT doctor_id from appointment where date = ?1 and status = 'free'))",
    nativeQuery = true)
    List<Clinic> findClinicsByDate(LocalDate date);
}
