package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicRating;
import com.example.Clinic.rest.support.dto.ClinicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ClinicToDto implements Converter<Clinic, ClinicDto> {

    @Autowired
    private DoctorToDto doctorToDto;

    @Autowired
    private AddressToDto addressToDto;

    @Autowired
    private ServiceToDto serviceToDto;

    @Override
    public ClinicDto convert(Clinic clinic) {
        ClinicDto dto = new ClinicDto();
        dto.setClinic_id(clinic.getClinic_id());
        dto.setName(clinic.getName());
        dto.setDescription(clinic.getDescription());
        //dto.setRating(clinic.getRating());
        dto.setAddressName(clinic.getAddressName());
        dto.setLng(clinic.getLng());
        dto.setLat(clinic.getLat());

        float total = 0;
        float average = 0;


        if (clinic.getRatings().size() != 0) {

            for (ClinicRating rating : clinic.getRatings()) {
                total += rating.getRating();
            }
            average = total / clinic.getRatings().size();
        }

        dto.setRating(average);

        return dto;
    }


    public List<ClinicDto> convertList(List<Clinic> source) {
        List<ClinicDto> ret = new ArrayList();

        for (Clinic z : source) {
            ret.add(this.convert(z));
        }

        return ret;
    }
}
