package com.example.Clinic.repository;

import com.example.Clinic.model.RegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRequestRepository extends JpaRepository<RegisterRequest, Long> {

    RegisterRequest findByPatientId(Long id);


}
