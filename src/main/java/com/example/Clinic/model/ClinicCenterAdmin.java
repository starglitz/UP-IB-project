package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class ClinicCenterAdmin extends User{
    @ManyToOne
    @JoinColumn(name="clinic_center_id")
    private ClinicCenter clinicCenter;
}
