package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Address;
import com.example.Clinic.rest.support.dto.AddressDto;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAddress implements Converter<AddressDto, Address> {

    @Autowired
    private ClinicService clinicService;

    @Override
    public Address convert(AddressDto dto) {
        Address address = new Address();

        address.setId(dto.getId());
        address.setName(dto.getName());
        address.setLng(dto.getLng());
        address.setLat(dto.getLat());
//        address.setClinic(dto.getClinic()); // TODO:to entity

//        if (dto.getId() != null) {
//            address = (Address) this.AddressService.findOne(address.getId()).get();
//        }

        if (address == null) {
            address = new Address();
        }

        return address;
    }
}
