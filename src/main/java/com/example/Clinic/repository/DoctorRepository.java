package com.example.Clinic.repository;

import com.example.Clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT * FROM doctor WHERE clinic_id = ?1",
            nativeQuery = true)
    List<Doctor> findByClinicId(Long clinic_id);

    @Query(value = "SELECT * from  doctor WHERE clinic_id = ?1\n" +
            "                        and id in (SELECT doctor_id from appointment where date = ?2 AND status = 'FREE')",
            nativeQuery = true)
    List<Doctor> findByClinicAndDate(Long clinic_id, LocalDate date);

    @Query(value = "SELECT * FROM doctor WHERE id in (" +
            "select doctor_id from appointment where patient_id = ?1 and status = 2)" +
            "and id not in(select doctor_id from doctor_ratings where ratings_id in " +
            "(select id from doctor_rating where patient_id = ?1))",
            nativeQuery = true)
    List<Doctor> findNotRatedByPatientId(Long clinic_id);
}
