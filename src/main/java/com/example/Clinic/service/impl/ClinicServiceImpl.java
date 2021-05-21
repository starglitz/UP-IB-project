package com.example.Clinic.service.impl;

import com.example.Clinic.dao.ClinicDao;
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
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic findById(Long id) {
        Optional<Clinic> clinic = clinicRepository.findById(id);
        return clinic.orElse(null);
    }

    @Override
    public List<Clinic> findClinicsByDate(LocalDate date) {
        List<Clinic> clinics = clinicRepository.findClinicsByDate(date);
        System.out.println(clinics);
        if(clinics.size() > 0){
            return clinics;
        }
        else if(date.equals(LocalDate.of(2000, 01, 01))){
            return clinicRepository.findAll();
        }
        else
            return new ArrayList<Clinic>();
    }

    @Override
    public Clinic update(Clinic clinic) {
        return clinicRepository.save(clinic);
    }
}
