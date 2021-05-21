package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.rest.support.dto.DoctorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoctorToDto implements Converter<Doctor, DoctorDto> {
    @Override
    public DoctorDto convert(Doctor doctor) {
        DoctorDto dto = new DoctorDto();

        dto.setId(doctor.getId());
        dto.setGrade(doctor.getGrade());
//        doctor.setClinic(); // TODO: convert to dto
//        doctor.setUser(); // TODO: convert to dto

        return dto;
    }
}