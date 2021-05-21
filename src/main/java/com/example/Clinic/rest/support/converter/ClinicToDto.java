package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.rest.support.dto.ClinicDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClinicToDto implements Converter<Clinic, ClinicDto> {
    @Override
    public ClinicDto convert(Clinic clinic) {
        ClinicDto dto = new ClinicDto();

        dto.setLat(clinic.getLat());
        dto.setLng(clinic.getLng());
        dto.setName(clinic.getName());
        dto.setDescription(clinic.getDescription());
        dto.setRating(clinic.getRating());
        dto.setAddressName(clinic.getAddressName());
//        dto.setDoctors();// TODO: convert to dto
//        dto.setAddress(); // TODO: convert to dto
//        dto.setServices();// TODO: convert to dto
        return null;
    }
}
