package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class Doctor extends User {

    private float grade;
    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;
}
