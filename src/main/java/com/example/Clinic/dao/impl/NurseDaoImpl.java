package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.NurseDao;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NurseDaoImpl implements NurseDao {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    @Override
    public Optional<Nurse> findById(Long id) {
        return nurseRepository.findById(id);
    }
}
