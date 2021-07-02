package com.example.Clinic.repository;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1)",
            nativeQuery = true)
    List<Appointment> findByClinicId(Long clinic_id);


    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1) and status='FREE'",
            nativeQuery = true)
    List<Appointment> findFreeByClinicId(Long clinic_id);

    @Query(value = "SELECT * FROM appointment WHERE doctor_id =?1 and date = ?2 and  status = 'FREE'",
            nativeQuery = true)
    List<Appointment> findFreeByDoctorAndDate(Long doctor_id, LocalDate date);

    List<Appointment> findAppointmentByPatientAndStatus(Patient patient, AppointmentStatus status);

}
