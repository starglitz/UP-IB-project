package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.ClinicDao;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClinicDaoImpl implements ClinicDao {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Optional<Clinic> findById(Long id) {
        return clinicRepository.findById(id);
    }
}
