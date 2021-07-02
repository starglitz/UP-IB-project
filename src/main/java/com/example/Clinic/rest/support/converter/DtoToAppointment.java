package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Address;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.rest.support.dto.AppointmentDto;
import com.example.Clinic.service.AppointmentService;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAppointment implements Converter<AppointmentDto, Appointment> {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DtoToPatient dtoToPatient;

    @Autowired
    private DtoToNurse dtoToNurse;

    @Autowired
    private DtoToDoctor dtoToDoctor;


    @Override
    public Appointment convert(AppointmentDto dto) {
        Appointment appointment = new Appointment();

        if (appointment.getAppointment_id() != null) {
            appointment = (Appointment) this.appointmentService.findById(appointment.getAppointment_id());
        }

        if (appointment == null) {
            appointment = new Appointment();
        }

        if(dto.getPatient() != null) {
            appointment.setPatient(dtoToPatient.convert(dto.getPatient()));
        }

        System.out.println(dto);

        Nurse nurse = nurseService.findById(dto.getNurse().getId());
        appointment.setNurse(nurse);

        Doctor doctor = doctorService.findById(dto.getDoctor().getId());
        appointment.setDoctor(doctor);

        appointment.setAppointment_id(dto.getAppointment_id());
        appointment.setDate(dto.getDate());
        appointment.setDeleted(dto.isDeleted());
        appointment.setStart(dto.getStart());
        appointment.setEnd(dto.getEnd());
        appointment.setStatus(dto.getStatus());
        appointment.setPrice(dto.getPrice());
        appointment.setConclusion(dto.getConclusion());


        return appointment;
    }
}