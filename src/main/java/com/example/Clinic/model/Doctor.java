package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Doctor{

    @Id
    private Long id;

    private float grade;
    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Doctor(User user) {
        this.user = user;
    }
}
