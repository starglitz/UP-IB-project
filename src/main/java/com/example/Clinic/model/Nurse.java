package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Nurse {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Nurse(User user) {
        this.user = user;
    }
}
