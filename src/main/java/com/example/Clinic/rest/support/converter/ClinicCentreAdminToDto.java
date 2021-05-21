package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenterAdmin;
import com.example.Clinic.rest.support.dto.ClinicCentreAdminDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicCentreAdminToDto implements Converter<ClinicCentreAdminDto, ClinicCenterAdmin> {
    @Override
    public ClinicCenterAdmin convert(ClinicCentreAdminDto dto) {
        ClinicCenterAdmin clinicCenterAdmin = new ClinicCenterAdmin();

        clinicCenterAdmin.setId(dto.getId());

//        clinicCenterAdmin.setClinicCenter();TODO: convert to dto
//        clinicCenterAdmin.setUser();TODO: convert to dto

        return clinicCenterAdmin;
    }
}
