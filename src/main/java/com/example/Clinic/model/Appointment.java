package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointment_id;
    private AppointmentStatus status = AppointmentStatus.FREE;
    private LocalDate date;
    private LocalTime time;
    private String duration;

    @ManyToOne
    private Doctor doctor;

    //(cascade = CascadeType.PERSIST)
    @ManyToOne
    private Nurse nurse;
    private int price;
    private boolean deleted;

}
