package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicAdminToDto implements Converter<ClinicAdmin, ClinicAdminDto> {

    @Override
    public ClinicAdminDto convert(ClinicAdmin clinicAdmin) {
        ClinicAdminDto dto = new ClinicAdminDto();

        dto.setId(clinicAdmin.getId());
//      dto.setUser(); TODO: convert to dto
//      dto.setClinic(); TODO: convert to dto

        return dto;
    }
}