package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Doctor;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.AppointmentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AppointmentDto {

    private Long appointment_id;

    private String conclusion;

    @NotNull
    private AppointmentStatus status;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime start;

    @NotNull
    private LocalTime end;

    @NotNull
    private DoctorDto doctor;

    @NotNull
    private NurseDto nurse;

    private PatientDto patient;

    @NotNull
    private int price;

    private boolean deleted;
}
