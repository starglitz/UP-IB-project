package com.example.Clinic.service.impl;


import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.repository.ClinicRepository;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Clinic create(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Optional<Clinic> findById(Long id) {
        return clinicRepository.findById(id);
    }

    @Override
    public List<Clinic> findClinicsByDate(LocalDate date) {
        List<Clinic> clinics = clinicRepository.findClinicsByDate(date);

        if(date.equals(LocalDate.of(2000, 01, 01))){
            return clinicRepository.findAll();
        }
        return clinics;

    }

    @Override
    public Clinic update(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> getNotRatedByPatientId(Long id) {
        return clinicRepository.findNotRatedByPatientId(id);
    }
}
