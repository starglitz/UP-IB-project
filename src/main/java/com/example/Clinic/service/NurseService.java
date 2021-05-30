package com.example.Clinic.service;

import com.example.Clinic.model.Nurse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NurseService {
    List<Nurse> findAll();
    Optional<Nurse> findById(Long id);
    Nurse create(Nurse nurse);
}
