package com.example.Clinic.repository;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    @Query(value = "SELECT * FROM nurse WHERE clinic_id = ?1",
            nativeQuery = true)
    List<Nurse> findByClinicId(Long clinic_id);
}
