package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Address;
import com.example.Clinic.rest.support.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToDto implements Converter<Address, AddressDto> {

    @Autowired
    private ClinicToDto toDto;

    @Override
    public AddressDto convert(Address address) {
        AddressDto dto = new AddressDto();

        dto.setId(address.getId());
        dto.setName(address.getName());
        dto.setLng(address.getLng());
        dto.setLat(address.getLat());
        dto.setClinic(toDto.convert(address.getClinic()));

        return dto;
    }
}