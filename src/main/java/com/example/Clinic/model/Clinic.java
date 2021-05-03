package com.example.Clinic.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clinic_id;
    private String name;
    private String description;
  //  private List<Doctor> doctors;
//    @OneToMany
//    @JoinColumn(name = "service_id")
//    private List<Service> services;
    private float rating;

}
