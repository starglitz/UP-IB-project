package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenter;
import com.example.Clinic.rest.support.dto.ClinicCentreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicCentreToDto implements Converter<ClinicCenter, ClinicCentreDto> {

    @Autowired
    private ClinicToDto clinicToDto;

    @Override
    public ClinicCentreDto convert(ClinicCenter clinicCenter) {
        ClinicCentreDto dto = new ClinicCentreDto();

        dto.setClinic_center_id(clinicCenter.getClinic_center_id());
        dto.setName(clinicCenter.getName());
        dto.setClinics(clinicToDto.convertList(clinicCenter.getClinics()));
        dto.setAddress(clinicCenter.getAddress());

        return dto;
    }
}
