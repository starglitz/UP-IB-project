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
            "                             (SELECT doctor_id from appointment where date = ?1 and status = 0  and date >= date(now())))",
    nativeQuery = true)
    List<Clinic> findClinicsByDate(LocalDate date);

    @Query(value = "SELECT * from  clinic where clinic_id in\n" +
            "(SELECT doctor.clinic_id from doctor where id in\n" +
            "                             (SELECT doctor_id from appointment where status = 0  and date >= date(now())))",
            nativeQuery = true)
    List<Clinic> findAllCurrentDates();



    @Query(value = "select * from  clinic where clinic_id in " +
            "(select clinic_id from doctor where id in(" +
            "select doctor_id from appointment where patient_id = ?1 and status = 2))" +
            "and clinic_id not in(select clinic_clinic_id from clinic_ratings " +
            "where ratings_id in (select id from clinic_rating where patient_id = ?1))",
            nativeQuery = true)
    List<Clinic> findNotRatedByPatientId(Long id);
}
