package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Authority;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.User;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.rest.ClinicAdminApi;
import com.example.Clinic.rest.support.converter.DtoToClinic;
import com.example.Clinic.rest.support.converter.DtoToClinicAdmin;
import com.example.Clinic.rest.support.converter.DtoToUser;
import com.example.Clinic.rest.support.converter.ReqisterDtoToClinicAdmin;
import com.example.Clinic.rest.support.dto.RegisterClinicAdminDto;
import com.example.Clinic.rest.support.dto.UserDto;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Component
public class ClinicAdminApiImpl implements ClinicAdminApi {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private ReqisterDtoToClinicAdmin registerDtoToClinicAdmin;



    @Override
    public ResponseEntity create(@Valid RegisterClinicAdminDto dto) {

        ClinicAdmin clinicAdmin = registerDtoToClinicAdmin.convert(dto);


        if (clinicAdmin != null)
            return new ResponseEntity<>(clinicAdminService.save(clinicAdmin), HttpStatus.CREATED);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
