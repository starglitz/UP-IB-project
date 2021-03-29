package com.example.Clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Patient extends User {
    private String lbo;
    private boolean enabled;


    public Patient( String email, String password, String name, String lastName,
                    String address, String city, String country, String phoneNumber,
                    String lbo, boolean enabled) {
        super(email, password, name, lastName, address, city, country, phoneNumber);
        this.lbo = lbo;
        this.enabled = enabled;
    }
}
