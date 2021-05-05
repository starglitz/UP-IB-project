package com.example.Clinic.service.impl;

import com.example.Clinic.dao.ClinicDao;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<Clinic> findClinicsByDate(LocalDate date) {
        List<Clinic> clinics = clinicDao.findClinicsByDate(date);
        System.out.println(clinics);
        if(clinics.size() > 0){
            return clinics;
        }
        else if(date.equals(LocalDate.of(2000, 01, 01))){
            return clinicDao.findAll();
        }
        else
            return new ArrayList<Clinic>();
    }

    @Override
    public Clinic update(Clinic clinic) {
        return clinicDao.update(clinic);
    }
}
