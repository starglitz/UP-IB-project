package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenter;
import com.example.Clinic.rest.support.dto.ClinicCentreDto;
import org.springframework.core.convert.converter.Converter;

public class ClinicCentreToDto implements Converter<ClinicCenter, ClinicCentreDto> {
    @Override
    public ClinicCentreDto convert(ClinicCenter clinicCenter) {
        ClinicCentreDto dto = new ClinicCentreDto();

        dto.setClinic_center_id(clinicCenter.getClinic_center_id());
        dto.setName(clinicCenter.getName());
//        dto.setClinics(); TODO: convert to dto
        dto.setAddress(clinicCenter.getAddress());

        return dto;
    }
}
