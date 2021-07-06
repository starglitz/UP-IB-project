package com.example.Clinic.service.impl;


import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.example.Clinic.repository.AppointmentRepository;
import com.example.Clinic.service.AppointmentService;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.NurseService;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private PatientService patientService;

    @Override
    public boolean add(Appointment appointment) {
        boolean valid = checkValid(appointment);

        if(appointment.getDate().equals(LocalDate.now())) {
            if(appointment.getStart().isBefore(LocalTime.now())
            || appointment.getEnd().isBefore(LocalTime.now())) {
                valid = false;
            }
        }
        if (valid) {

            boolean notBooked = true;
            List<Appointment> all = appointmentRepository.findAll();
            for(Appointment app : all) {
                if(app.getDate().equals(appointment.getDate())) {

                    if (app.getDoctor().equals(appointment.getDoctor()) ||
                            app.getNurse().equals(appointment.getNurse())) {

                        Boolean startInZone = (
                                (app.getStart().isAfter(appointment.getStart()))
                                        && (app.getStart().isBefore(appointment.getEnd()))
                        );
                        Boolean endInZone = (
                                (app.getEnd().isAfter(appointment.getStart()))
                                        && (app.getEnd().isBefore(appointment.getEnd()))
                        );

                        Boolean isTheSame = (app.getEnd().equals(appointment.getEnd()) ||
                                app.getStart().equals(appointment.getStart()));

                        Boolean inZone = appointment.getStart().isAfter(app.getStart()) &&
                                appointment.getEnd().isBefore(app.getEnd());

                        if (startInZone || endInZone || isTheSame || inZone) {
                            notBooked = false;
                            valid = false;
                        }
                    }
                }
            }
            if(notBooked) {
                Doctor doctor = doctorService.findById(appointment.getDoctor().getId());
                Nurse nurse = nurseService.findById(appointment.getNurse().getId());

                appointment.setDoctor(doctor);
                appointment.setNurse(nurse);
                appointment.setPatient(null);
                appointment.setStatus(AppointmentStatus.FREE);

                System.out.println(appointment);
                appointmentRepository.save(appointment);
            }
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
    public List<Appointment> findByDoctor(Doctor doctor) {
        return appointmentRepository.findAppointmentByDoctor(doctor);
    }

    @Override
    public List<Appointment> findByNurse(Nurse nurse) {
        return appointmentRepository.findAppointmentByNurse(nurse);
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
    public List<Appointment> findFreeByDoctorAndDate(Long doctor_id, LocalDate date) {
        return appointmentRepository.findFreeByDoctorAndDate(doctor_id, date);
    }

    @Override
    public List<Appointment> findByPatient(long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(value ->
                appointmentRepository.findAppointmentByPatientAndStatus(value, AppointmentStatus.RESERVED))
                .orElse(null);
    }

    @Override
    public HashMap<String, Integer> findNumberByMonts(Long id) {
        List<Appointment> appointments = appointmentRepository.findPassedByClinicId(id);
        HashMap<String, Integer> appointmentsByMonths = new HashMap<>();
        appointmentsByMonths.put("JANUARY", 0);
        appointmentsByMonths.put("FEBRUARY", 0);
        appointmentsByMonths.put("MARCH", 0);
        appointmentsByMonths.put("APRIL", 0);
        appointmentsByMonths.put("MAY", 0);
        appointmentsByMonths.put("JUNE", 0);
        appointmentsByMonths.put("JULY", 0);
        appointmentsByMonths.put("AUGUST", 0);
        appointmentsByMonths.put("SEPTEMBER", 0);
        appointmentsByMonths.put("OCTOBER", 0);
        appointmentsByMonths.put("NOVEMBER", 0);
        appointmentsByMonths.put("DECEMBER", 0);
        for(Appointment a : appointments){
            Month month = a.getDate().getMonth();
            Integer count = appointmentsByMonths.get(month.toString());
            appointmentsByMonths.put(month.toString(), count+1);
        }

        return appointmentsByMonths;
    }

    @Override
    public HashMap<Integer, Integer> findNumberByWeeks(Long id) {
        List<Appointment> appointments = appointmentRepository.findPassedByClinicId(id);
        HashMap<Integer, Integer> appointmentsByWeeks = new HashMap<>();
        for(int i = 1; i < 53;i++){
            appointmentsByWeeks.put(i, 0);
        }
        for(Appointment a : appointments){
            TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int weekNumber = a.getDate().get(woy);
            Integer count = appointmentsByWeeks.get(weekNumber);
            appointmentsByWeeks.put(weekNumber, count+1);


        }
        return appointmentsByWeeks;
    }

    @Override
    public HashMap<Integer, Integer> findNumberByDaysInMonth(Long id,String month) {
        List<Appointment> appointments = appointmentRepository.countAppointmentsByMonth(month, id);
        HashMap<Integer, Integer> appointmentsByWeeks = new HashMap<>();
        if(month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
            for (int i = 1; i < 31; i++) {
                appointmentsByWeeks.put(i, 0);
            }
        }
        else if(month.equals("February")){
            for (int i = 1; i < 29; i++) {
                appointmentsByWeeks.put(i, 0);
            }
        }
        else{
            for (int i = 1; i < 32; i++) {
                appointmentsByWeeks.put(i, 0);
            }
        }

        for(Appointment a : appointments){
            int day = a.getDate().getDayOfMonth();
            Integer count = appointmentsByWeeks.get(day);
            appointmentsByWeeks.put(day, count+1);

        }

        return appointmentsByWeeks;
    }

    @Override
    public Integer findIncomeBetweenDates(Long id,LocalDate dateFrom, LocalDate dateTo) {
        List<Appointment> appointments = appointmentRepository.findIncomeBetweenDates(id, dateFrom, dateTo);
        Integer income = 0;
        for (Appointment a : appointments) {
            income += a.getPrice();

        }
        return income;
    }

    public List<Appointment> findByPatientFinished(long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(value ->
                appointmentRepository.findAppointmentByPatientAndStatus(value, AppointmentStatus.PASSED))
                .orElse(null);

    }


    private boolean checkValid(Appointment appointment) {
        boolean valid = true;

        if (appointment.getDate() == null) { valid = false; }
        if (appointment.getStart() == null) { valid = false; }
        if (appointment.getEnd() == null) { valid = false; }
        if (appointment.getDoctor() == null) { valid = false; }
        if (appointment.getNurse() == null) { valid = false; }

        return valid;
    }

}
