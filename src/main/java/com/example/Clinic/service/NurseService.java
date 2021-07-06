package com.example.Clinic.service;

import com.example.Clinic.model.Nurse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NurseService {
    List<Nurse> findAll();
    Nurse findById(Long id);
    boolean create(Nurse nurse, Authentication authentication);
    List<Nurse> findByClinicId(Long id);
}
