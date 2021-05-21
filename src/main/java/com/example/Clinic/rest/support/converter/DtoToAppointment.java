package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Address;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import com.example.Clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAppointment implements Converter<AppointmentDto, Appointment> {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public Appointment convert(AppointmentDto dto) {
        Appointment appointment = new Appointment();

        appointment.setAppointment_id(dto.getAppointment_id());
        appointment.setDate(dto.getDate());
        appointment.setDeleted(dto.isDeleted());
        appointment.setStart(dto.getStart());
        appointment.setEnd(dto.getEnd());
        appointment.setPrice(dto.getPrice());
//      appointment.setPatient(); // TODO: convert to patient
//      appointment.setNurse(); // TODO: covert to nurse
//      appointment.setDoctor(); // TODO: convert to doctor

        if (appointment.getAppointment_id() != null) {
            appointment = (Appointment) this.appointmentService.findById(appointment.getAppointment_id());
        }

        if (appointment == null) {
            appointment = new Appointment();
        }

        return appointment;
    }
}