package com.example.Clinic.dao;

import com.example.Clinic.model.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDao {

    public List<Service> findByClinicId(Long clinic_id);

}
