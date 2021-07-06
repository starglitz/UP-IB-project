package com.example.Clinic.repository;

import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1)",
            nativeQuery = true)
    List<Appointment> findByClinicId(Long clinic_id);


    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1) and status=0 and appointment.date >= date(now())",
            nativeQuery = true)
    List<Appointment> findFreeByClinicId(Long clinic_id);

    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1) and status=2",
            nativeQuery = true)
    List<Appointment> findPassedByClinicId(Long clinic_id);


    @Query(value = "SELECT * FROM appointment WHERE doctor_id in (SELECT id from doctor WHERE clinic_id = ?1) and date between ?2 and ?3 and status =2",
            nativeQuery = true)
    List<Appointment> findIncomeBetweenDates(Long id, LocalDate dateFrom, LocalDate dateTo);

    @Query(value = "SELECT * FROM appointment WHERE MONTHNAME(date) = ?1 and doctor_id in (SELECT id from doctor WHERE clinic_id = ?2) and status =2",
            nativeQuery = true)
    List<Appointment> countAppointmentsByMonth(String month, Long id);


    @Query(value = "SELECT * FROM appointment WHERE doctor_id =?1 and date = ?2 and  status = 0",
            nativeQuery = true)
    List<Appointment> findFreeByDoctorAndDate(Long doctor_id, LocalDate date);

    List<Appointment> findAppointmentByPatientAndStatus(Patient patient, AppointmentStatus status);

    Appointment findAppointmentByDoctorAndConclusion(Doctor doctor, String conclusion);

    List<Appointment> findAppointmentByNurse(Nurse nurse);

    List<Appointment> findAppointmentByDoctor(Doctor doctor);

}
