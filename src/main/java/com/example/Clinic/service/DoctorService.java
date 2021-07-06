package com.example.Clinic.service;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.DoctorRating;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {
    List<Doctor> findAll();
    Doctor findById(Long id);
    List<Doctor> findByClinicId(Long id);
    List<Doctor> findByClinicAndDate(Long id, LocalDate date);
    boolean create(Doctor doctor, Authentication authentication);
    List<Doctor> getNotRatedByPatientId(Long id);
    Doctor rate(Long id, DoctorRating rating);
}
