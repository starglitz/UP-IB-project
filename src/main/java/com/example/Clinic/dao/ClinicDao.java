package com.example.Clinic.dao;

import com.example.Clinic.model.Clinic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicDao {
    List<Clinic> findAll();
    Optional<Clinic> findById(Long id);
}
