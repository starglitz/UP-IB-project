package com.example.Clinic.service;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicRating;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    Clinic create(Clinic clinic);
    List<Clinic> findAll();
    Clinic findById(Long id);
    List<Clinic> findClinicsByDate(LocalDate date);
    Clinic update(Clinic clinic);
    List<Clinic> getNotRatedByPatientId(Long id);
    Clinic rate(Long id, ClinicRating rating);
}
