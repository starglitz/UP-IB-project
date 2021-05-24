package com.example.Clinic.service.impl;

import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.repository.RegisterRequestRepository;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {


    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Override
    public RegisterRequest addRegisterRequest(RegisterRequest request) {
        return registerRequestRepository.save(request);
    }

    @Override
    public List<RegisterRequest> getAll() {
        return  new ArrayList<RegisterRequest>(registerRequestRepository.findAll());
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
