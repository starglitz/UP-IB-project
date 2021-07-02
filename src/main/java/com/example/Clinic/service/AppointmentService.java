package com.example.Clinic.service;

import com.example.Clinic.model.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    boolean add(Appointment appointment);
    boolean update(Appointment appointment);
    Appointment delete(Appointment appointment);
    Appointment findById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByClinicId(Long clinic_id);
    List<Appointment> findFreeByClinicId(Long clinic_id);
    List<Appointment> findFreeByDoctorAndDate(Long doctor_id, LocalDate date);
    List<Appointment> findByPatient(long id);
}
