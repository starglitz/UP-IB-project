package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicRating;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.DoctorRating;
import com.example.Clinic.rest.support.dto.ClinicDto;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.rest.support.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorToDto implements Converter<Doctor, DoctorDto> {

    @Autowired
    private ClinicToDto clinicToDto;

    @Autowired
    private UserToDto userToDto;

    @Override
    public DoctorDto convert(Doctor doctor) {
        DoctorDto dto = new DoctorDto();

        dto.setId(doctor.getId());
        //dto.setGrade(doctor.getGrade());
        if(doctor.getClinic() != null) {
            dto.setClinic(clinicToDto.convert(doctor.getClinic()));
        }
        dto.setUser(userToDto.convert(doctor.getUser()));


        float total = 0;
        float average = 0;


        if (doctor.getRatings().size() != 0) {

            for (DoctorRating rating : doctor.getRatings()) {

                    total += rating.getRating();

            }
            average = total / doctor.getRatings().size();
        }

        dto.setGrade(average);


        return dto;
    }


    public List<DoctorDto> convertList(List<Doctor> source) {
        List<DoctorDto> ret = new ArrayList();

        for (Doctor z : source) {
            ret.add(this.convert(z));
        }

        return ret;
    }
}