package com.example.Clinic.service;

import com.example.Clinic.model.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    Address findById(Long id);
}
