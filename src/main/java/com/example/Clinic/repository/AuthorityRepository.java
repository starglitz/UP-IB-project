package com.example.Clinic.repository;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Authority;
import com.example.Clinic.model.enumerations.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query(value = "SELECT * FROM authority WHERE name = ?1",
            nativeQuery = true)
    Authority findByName(String name);
}
