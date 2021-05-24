package com.example.Clinic.service.impl;


import com.example.Clinic.model.Doctor;
import com.example.Clinic.repository.DoctorRepository;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findByClinicId(Long id) {
        return doctorRepository.findByClinicId(id);
    }

    @Override
    public List<Doctor> findByClinicAndDate(Long id, LocalDate date) {
        return doctorRepository.findByClinicAndDate(id, date);
    }
}
