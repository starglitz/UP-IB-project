package com.example.Clinic.service;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    boolean add(Appointment appointment);
    boolean update(Appointment appointment);
    Appointment delete(Appointment appointment);
    Appointment findById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByNurse(Nurse nurse);
    List<Appointment> findByClinicId(Long clinic_id);
    List<Appointment> findFreeByClinicId(Long clinic_id);
    List<Appointment> findFreeByDoctorAndDate(Long doctor_id, LocalDate date);
    List<Appointment> findByPatient(long id);
    HashMap<String, Integer> findNumberByMonts(Long id);
    HashMap<Integer, Integer> findNumberByWeeks(Long id);
    HashMap<Integer, Integer> findNumberByDaysInMonth(Long id,String month);
    Integer findIncomeBetweenDates(Long id,LocalDate dateFrom, LocalDate dateTo);
    List<Appointment> findByPatientFinished(long id);
}
