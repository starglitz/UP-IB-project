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
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
