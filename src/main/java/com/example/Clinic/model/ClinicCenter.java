package com.example.Clinic.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class ClinicCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clinic_center_id;
    private String name;
    private String address;
    @OneToMany
    @JoinColumn(name = "clinic_id")
    private List<Clinic> clinics;
}
