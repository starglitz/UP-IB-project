package com.example.Clinic.service.impl;

import com.example.Clinic.dao.AppointmentDao;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public boolean add(Appointment appointment) {
        boolean valid = checkValid(appointment);

        if (valid) { appointmentDao.add(appointment); }

        return valid;
    }

    @Override
    public boolean update(Appointment appointment) {
        boolean valid = checkValid(appointment);

        if (valid) { appointmentDao.update(appointment); }

        return valid;
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

    @Override
    public List<Appointment> findFreeByClinicId(Long clinic_id) {
        return appointmentDao.findFreeByClinicId(clinic_id);
    }

    @Override
    public List<Appointment> findFreeByDoctor(Long doctor_id) {
        return appointmentDao.findFreeByDoctor(doctor_id);
    }


    private boolean checkValid(Appointment appointment) {
        boolean valid = true;

        if (appointment.getDate() == null) { valid = false; }
        if (appointment.getStart() == null) { valid = false; }
        if (appointment.getEnd() == null) { valid = false; }
        if (appointment.getDoctor() == null) { valid = false; }
        if (appointment.getNurse() == null) { valid = false; }
        if (appointment.getPatient() == null) { valid = false; }

        return valid;
    }

}
