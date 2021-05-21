package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToDoctor implements Converter<DoctorDto, Doctor> {

    @Autowired
    private DoctorService doctorService;

    @Override
    public Doctor convert(DoctorDto dto) {
        Doctor doctor = new Doctor();

        doctor.setId(dto.getId());
        doctor.setGrade(dto.getGrade());
//        doctor.setClinic(); // TODO: convert to entity
//        doctor.setUser(); // TODO: convert to entity

        if (doctor.getId() != null) {
            doctor = (Doctor) this.doctorService.findById(doctor.getId()).get();
        }

        if (doctor == null) {
            doctor = new Doctor();
        }

        return doctor;
    }
}
