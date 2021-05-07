package com.example.Clinic.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clinic_id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
  //  private List<Doctor> doctors;
//    @OneToMany
//    @JoinColumn(name = "service_id")
//    private List<Service> services;
    private float rating;


//    @OneToOne(mappedBy = "clinic")
//    private Address address;


    private String addressName;

    private String lat;

    private String lng;

}
