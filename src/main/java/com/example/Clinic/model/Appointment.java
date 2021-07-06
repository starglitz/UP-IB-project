package com.example.Clinic.model;

import com.example.Clinic.model.enumerations.AppointmentStatus;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointment_id;

    private String conclusion;

    private AppointmentStatus status;

    private LocalDate date;

    private LocalTime start;

    private LocalTime end;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Nurse nurse;


    @ManyToOne
    @Nullable
    private Patient patient;
    
    private int price;
    private boolean deleted;

    public Appointment(AppointmentStatus status, @NotNull @NotEmpty @Future LocalDate date,
                       LocalTime start, LocalTime end,
                       Doctor doctor, Nurse nurse, Patient patient, @Min(1) @NotEmpty int price,
                       boolean deleted) {
        this.status = status;
        this.date = date;
        this.start = start;
        this.end = end;
        this.doctor = doctor;
        this.nurse = nurse;
        this.patient = patient;
        this.price = price;
        this.deleted = deleted;
    }
}
