package com.example.Clinic.dao;

import com.example.Clinic.model.RegisterRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RegisterRequestDao {

    public RegisterRequest addRegisterRequest(RegisterRequest request);
    public Set<RegisterRequest> getAll();
    public RegisterRequest findByPatientId(Long id);
    public RegisterRequest update(RegisterRequest request);
    public Optional<RegisterRequest> findById(Long id);

}
