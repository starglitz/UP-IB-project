package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicAdminToDto implements Converter<ClinicAdmin, ClinicAdminDto> {

    @Autowired
    private ClinicToDto clinicToDto;
    @Autowired
    private UserToDto userToDto;

    @Override
    public ClinicAdminDto convert(ClinicAdmin clinicAdmin) {
        ClinicAdminDto dto = new ClinicAdminDto();

        dto.setId(clinicAdmin.getId());
        dto.setClinic(clinicToDto.convert(clinicAdmin.getClinic()));
        dto.setUser(userToDto.convert(clinicAdmin.getUser()));

        return dto;
    }
}