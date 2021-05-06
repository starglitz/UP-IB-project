package com.example.Clinic.model;

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

    private AppointmentStatus status = AppointmentStatus.FREE;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime start;

    @NotNull
    private LocalTime end;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Nurse nurse;
    
    @NotNull
    private int price;
    private boolean deleted;

    public Appointment(AppointmentStatus status, @NotNull @NotEmpty @Future LocalDate date,
                       LocalTime start, LocalTime end,
                       Doctor doctor, Nurse nurse, @Min(1) @NotEmpty int price,
                       boolean deleted) {
        this.status = status;
        this.date = date;
        this.start = start;
        this.end = end;
        this.doctor = doctor;
        this.nurse = nurse;
        this.price = price;
        this.deleted = deleted;
    }
}
