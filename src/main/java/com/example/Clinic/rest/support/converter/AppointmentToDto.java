package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppointmentToDto implements Converter<Appointment, AppointmentDto> {

    @Override
    public AppointmentDto convert(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();

        dto.setAppointment_id(appointment.getAppointment_id());
        dto.setDate(appointment.getDate());
        dto.setDeleted(appointment.isDeleted());
        dto.setStart(appointment.getStart());
        dto.setEnd(appointment.getEnd());
        dto.setPrice(appointment.getPrice());
//      dto.setPatient(); // TODO: convert to patientDto
//      dto.setNurse(); // TODO: covert to nurseDto
//      dto.setDoctor(); // TODO: convert to doctorDto

        return dto;
    }
}
