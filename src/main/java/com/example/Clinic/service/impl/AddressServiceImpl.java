package com.example.Clinic.service.impl;

import com.example.Clinic.model.Address;
import com.example.Clinic.repository.AddressRepository;
import com.example.Clinic.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }
}
