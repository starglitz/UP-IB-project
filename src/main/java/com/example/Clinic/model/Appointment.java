package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointment_id;
    private AppointmentStatus status;
    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAndTime;
    private String duration;

    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Nurse nurse;
    private int price;
    private boolean deleted;

}
