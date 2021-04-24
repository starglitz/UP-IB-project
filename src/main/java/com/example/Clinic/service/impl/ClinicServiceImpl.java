package com.example.Clinic.service.impl;

import com.example.Clinic.dao.ClinicDao;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicDao clinicDao;

    @Override
    public List<Clinic> findAll() {
        return clinicDao.findAll();
    }

    @Override
    public Clinic findById(Long id) {
        return clinicDao.findById(id).get();
    }
}
