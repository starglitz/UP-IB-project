package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Nurse extends User{

    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;

    public Nurse(String email, String password, String name, String lastName,
                  String address, String city, String country, String phoneNumber) {
        super(email, password, name, lastName, address, city, country, phoneNumber);
    }
}
