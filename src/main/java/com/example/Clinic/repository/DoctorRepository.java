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
            "                        and id in (SELECT doctor_id from appointment where date = ?2)",
            nativeQuery = true)
    List<Doctor> findByClinicAndDate(Long clinic_id, LocalDate date);
}
