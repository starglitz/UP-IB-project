package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.RegisterRequestDao;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.repository.RegisterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RegisterRequestDaoImpl implements RegisterRequestDao {

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Override
    public RegisterRequest addRegisterRequest(RegisterRequest request) {
        return registerRequestRepository.save(request);
    }

    @Override
    public Set<RegisterRequest> getAll() {
        return  new HashSet<RegisterRequest>(registerRequestRepository.findAll());
    }

    @Override
    public RegisterRequest findByPatientId(Long id) {
        return registerRequestRepository.findByPatientId(id);
    }

    @Override
    public RegisterRequest update(RegisterRequest request) {
        RegisterRequest requestJpa = findById(request.getRegister_request_id()).get();
        requestJpa.setStatus(request.getStatus());
        requestJpa.setVisitedMail(request.isVisitedMail());
        return registerRequestRepository.save(requestJpa);
    }

    @Override
    public Optional<RegisterRequest> findById(Long id) {
        return registerRequestRepository.findById(id);
    }


}
