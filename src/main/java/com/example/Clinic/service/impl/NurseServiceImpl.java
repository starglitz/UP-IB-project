package com.example.Clinic.service.impl;

import com.example.Clinic.dao.NurseDao;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseDao nurseDao;

    @Override
    public List<Nurse> findAll() {
        return nurseDao.findAll();
    }

    @Override
    public Optional<Nurse> findById(Long id) {
        return nurseDao.findById(id);
    }
}
