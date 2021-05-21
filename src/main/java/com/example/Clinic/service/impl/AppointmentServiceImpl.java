package com.example.Clinic.service.impl;

import com.example.Clinic.dao.AppointmentDao;
import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.AppointmentRepository;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public boolean add(Appointment appointment) {
        boolean valid = checkValid(appointment);

        if (valid) {
            appointmentRepository.save(appointment);
        }

        return valid;
    }

    @Override
    public boolean update(Appointment appointment) {
        boolean valid = checkValid(appointment);

        if (valid) {
            appointmentRepository.save(appointment);
        }

        return valid;
    }
    @Override
    public Appointment delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
        return appointment;
    }

    @Override
    public Appointment findById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);
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

    @Override
    public List<Appointment> findFreeByDoctor(Long doctor_id) {
        return appointmentRepository.findFreeByDoctor(doctor_id);
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
