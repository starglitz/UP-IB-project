package com.example.Clinic.service;

import com.example.Clinic.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    public boolean add(Appointment appointment);
    public boolean update(Appointment appointment);
    public Appointment delete(Appointment appointment);
    public Appointment findById(Long id);
    public List<Appointment> findAll();
    public List<Appointment> findByClinicId(Long clinic_id);
    public List<Appointment> findFreeByClinicId(Long clinic_id);
    List<Appointment> findFreeByDoctor(Long doctor_id);

}
