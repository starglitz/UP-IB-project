package com.example.Clinic.service;

import com.example.Clinic.model.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface RegisterRequestService {

    public RegisterRequest addRegisterRequest(RegisterRequest request);
    public List<RegisterRequest> getAll();
    public RegisterRequest findByPatientId(Long id);
    public Optional<RegisterRequest> findById(Long id);
    public RegisterRequest update(RegisterRequest request);


}
