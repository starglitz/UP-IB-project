package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenterAdmin;
import com.example.Clinic.rest.support.dto.ClinicCentreAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicCentreAdminToDto implements Converter<ClinicCenterAdmin, ClinicCentreAdminDto> {

    @Autowired
    private ClinicCentreToDto clinicCentreToDto;

    @Autowired
    private UserToDto userToDto;

    @Override
    public ClinicCentreAdminDto convert(ClinicCenterAdmin clinicCenterAdmin) {
        ClinicCentreAdminDto dto = new ClinicCentreAdminDto();

        dto.setId(dto.getId());
        dto.setClinicCenter(clinicCentreToDto.convert(clinicCenterAdmin.getClinicCenter()));
        dto.setUser(userToDto.convert(clinicCenterAdmin.getUser()));

        return dto;
    }
}
