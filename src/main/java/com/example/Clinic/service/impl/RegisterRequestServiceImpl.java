package com.example.Clinic.service.impl;

import com.example.Clinic.dao.RegisterRequestDao;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {

    @Autowired
    private RegisterRequestDao registerRequestDao;

    @Override
    public RegisterRequest addRegisterRequest(RegisterRequest request) {
        return registerRequestDao.addRegisterRequest(request);
    }

    @Override
    public Set<RegisterRequest> getAll() {
        return registerRequestDao.getAll();
    }

    @Override
    public RegisterRequest findByPatientId(Long id) {
        return registerRequestDao.findByPatientId(id);
    }
}
