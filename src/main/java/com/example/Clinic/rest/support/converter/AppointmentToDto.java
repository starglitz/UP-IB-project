package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppointmentToDto implements Converter<Appointment, AppointmentDto> {

    @Autowired
    private PatientToDto patientToDto;
    @Autowired
    private DoctorToDto doctorToDto;
    @Autowired
    private NurseToDto nurseToDto;

    @Override
    public AppointmentDto convert(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();

        dto.setAppointment_id(appointment.getAppointment_id());
        dto.setDate(appointment.getDate());
        dto.setDeleted(appointment.isDeleted());
        dto.setStart(appointment.getStart());
        dto.setEnd(appointment.getEnd());
        dto.setPrice(appointment.getPrice());
        dto.setPatient(patientToDto.convert(appointment.getPatient()));
        dto.setNurse(nurseToDto.convert(appointment.getNurse()));
        dto.setDoctor(doctorToDto.convert(appointment.getDoctor()));

        return dto;
    }
}
