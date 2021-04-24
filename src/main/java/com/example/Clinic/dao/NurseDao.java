package com.example.Clinic.dao;

import com.example.Clinic.model.Nurse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NurseDao {
    List<Nurse> findAll();
    Optional<Nurse> findById(Long id);
}
