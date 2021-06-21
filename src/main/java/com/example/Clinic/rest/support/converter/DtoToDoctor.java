package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.DoctorDto;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import com.example.Clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DtoToDoctor implements Converter<DoctorDto, Doctor> {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DtoToClinic dtoToClinic;

    @Autowired
    private DtoToUser dtoToUser;

    @Override
    public Doctor convert(DoctorDto dto) {
        Doctor doctor = new Doctor();

        if (doctor.getId() != null) {
            doctor = (Doctor) this.doctorService.findById(doctor.getId());
        }

        if (doctor == null) {
            doctor = new Doctor();
        }

        doctor.setId(dto.getId());
        //doctor.setGrade(dto.getGrade());
        doctor.setClinic(dtoToClinic.convert(dto.getClinic()));
        doctor.setUser(dtoToUser.convert(dto.getUser()));


        return doctor;
    }
}
