package com.example.Clinic.service.impl;

import com.example.Clinic.dao.AppointmentDao;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public Appointment add(Appointment appointment) {
        return appointmentDao.add(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentDao.update(appointment);
    }

    @Override
    public Appointment delete(Appointment appointment) {
        return appointmentDao.delete(appointment);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentDao.findById(id).get();
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    @Override
    public List<Appointment> findByClinicId(Long clinic_id) {
        return appointmentDao.findByClinicId(clinic_id);
    }
}
