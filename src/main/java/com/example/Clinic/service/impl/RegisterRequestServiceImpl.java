package com.example.Clinic.service.impl;

import com.example.Clinic.dao.RegisterRequestDao;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.RequestStatus;
import com.example.Clinic.repository.RegisterRequestRepository;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {


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

//        RegisterRequest requestJpa = registerRequestDao.findById(request.getRegister_request_id()).get();
//        requestJpa.setStatus(request.getStatus());
//        requestJpa.setVisitedMail(request.isVisitedMail());
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
