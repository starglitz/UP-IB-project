package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Service;
import com.example.Clinic.rest.ServiceApi;
import com.example.Clinic.rest.support.converter.ServiceToDto;
import com.example.Clinic.rest.support.dto.ServiceDto;
import com.example.Clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceApiImpl implements ServiceApi {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceToDto serviceToDto;

    @Override
    public ResponseEntity getClinicServices(Long id) {
        List<Service> services = serviceService.findByClinicId(id);
        List<ServiceDto> dtos = new ArrayList<>();
        for(Service service : services) {
            ServiceDto dto = serviceToDto.convert(service);
            dtos.add(dto);
        }

        return new ResponseEntity(dtos, HttpStatus.OK);
    }
}
