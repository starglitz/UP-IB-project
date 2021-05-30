package com.example.Clinic.service;

import com.example.Clinic.model.Clinic;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    Clinic create(Clinic clinic);
    List<Clinic> findAll();
    Optional<Clinic> findById(Long id);
    List<Clinic> findClinicsByDate(LocalDate date);
    Clinic update(Clinic clinic);
}
