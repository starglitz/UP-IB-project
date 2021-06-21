package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Doctor{

    @Id
    private Long id;

    @OneToMany
    private List<DoctorRating> ratings;

    @Transient
    private float averageRating;

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
