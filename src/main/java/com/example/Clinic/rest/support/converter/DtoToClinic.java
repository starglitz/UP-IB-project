package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.ClinicDto;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DtoToClinic implements Converter<ClinicDto, Clinic> {

    @Autowired
    private ClinicService clinicService;

    @Override
    public Clinic convert(ClinicDto dto) {
        Clinic clinic = new Clinic();

        if (clinic.getClinic_id() != null) {
            clinic = (Clinic) this.clinicService.findById(clinic.getClinic_id());
        }

        if (clinic == null) {
            clinic = new Clinic();
        }

        clinic.setLat(dto.getLat());
        clinic.setLng(dto.getLng());
        clinic.setName(dto.getName());
        clinic.setDescription(dto.getDescription());
        //clinic.setRating(dto.getRating());
        clinic.setAddressName(dto.getAddressName());


        return clinic;
    }

    public List<Clinic> convertList(List<ClinicDto> source) {
        List<Clinic> ret = new ArrayList();

        for (ClinicDto z : source) {
            ret.add(this.convert(z));
        }

        return ret;
    }
}

