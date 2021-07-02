package com.example.Clinic.service;

import com.example.Clinic.model.Nurse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NurseService {
    List<Nurse> findAll();
    Nurse findById(Long id);
    Nurse create(Nurse nurse);
    List<Nurse> findByClinicId(Long id);
}
