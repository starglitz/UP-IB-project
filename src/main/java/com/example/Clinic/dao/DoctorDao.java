package com.example.Clinic.dao;

import com.example.Clinic.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorDao {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
}
