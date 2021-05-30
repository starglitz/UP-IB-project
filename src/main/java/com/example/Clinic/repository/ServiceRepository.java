package com.example.Clinic.repository;

import com.example.Clinic.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(value = "SELECT * FROM service WHERE clinic_id = ?1",
            nativeQuery = true)
    List<Service> findByClinicId(Long clinic_id);

}
