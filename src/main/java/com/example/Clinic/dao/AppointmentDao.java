package com.example.Clinic.dao;

import com.example.Clinic.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDao {

    public Appointment add(Appointment appointment);
    public Appointment update(Appointment appointment);
    public Appointment delete(Appointment appointment);
    public Optional<Appointment> findById(Long id);
    public List<Appointment> findAll();
    public List<Appointment> findByClinicId(Long clinic_id);
    public List<Appointment> findFreeByClinicId(Long clinic_id);
}
