package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.AppointmentDao;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppointmentDaoImpl implements AppointmentDao {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment add(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {

        Appointment appointmentJpa = findById(appointment.getAppointment_id()).get();

        appointmentJpa.setStatus(appointment.getStatus());
        appointmentJpa.setDate(appointment.getDate());
        appointmentJpa.setTime(appointment.getTime());
        appointmentJpa.setDoctor(appointment.getDoctor());
        appointmentJpa.setNurse(appointment.getNurse());
        appointmentJpa.setDuration(appointment.getDuration());
        appointmentJpa.setPrice(appointment.getPrice());

        return appointmentRepository.save(appointmentJpa);
    }

    @Override
    public Appointment delete(Appointment appointment) {

        Appointment appointmentJpa = findById(appointment.getAppointment_id()).get();
        System.out.println("!!!!!!!!!!!!!!!!" + appointmentJpa);
        appointmentJpa.setDeleted(true);
        appointmentRepository.save(appointmentJpa);
        return appointmentJpa;
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByClinicId(Long clinic_id) {
        return appointmentRepository.findByClinicId(clinic_id);
    }

    @Override
    public List<Appointment> findFreeByClinicId(Long clinic_id) {
        return appointmentRepository.findFreeByClinicId(clinic_id);
    }


}
