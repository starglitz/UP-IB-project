package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.RegisterRequestDao;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.repository.RegisterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegisterRequestDaoImpl implements RegisterRequestDao {

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Override
    public RegisterRequest addRegisterRequest(RegisterRequest request) {
        return registerRequestRepository.save(request);
    }

    @Override
    public List<RegisterRequest> getAll() {
        return registerRequestRepository.findAll();
    }

    @Override
    public RegisterRequest findByPatientId(Long id) {
        return registerRequestRepository.findByPatientId(id);
    }
}