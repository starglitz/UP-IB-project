package com.example.Clinic.service.impl;

import com.example.Clinic.dao.DoctorDao;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorDao.findById(id);
    }

    @Override
    public List<Doctor> findByClinicId(Long id) {
        return doctorDao.findByClinicId(id);
    }

    @Override
    public List<Doctor> findByClinicAndDate(Long id, LocalDate date) {
        return doctorDao.findByClinicAndDate(id, date);
    }
}
