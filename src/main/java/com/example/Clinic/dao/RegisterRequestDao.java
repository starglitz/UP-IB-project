package com.example.Clinic.dao;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.RegisterRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRequestDao {

    public RegisterRequest addRegisterRequest(RegisterRequest request);
    public List<RegisterRequest> getAll();
    public RegisterRequest findByPatientId(Long id);
}