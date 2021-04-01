package com.example.Clinic.service;

import com.example.Clinic.model.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegisterRequestService {

    public RegisterRequest addRegisterRequest(RegisterRequest request);
    public List<RegisterRequest> getAll();
    public RegisterRequest findByPatientId(Long id);

}
