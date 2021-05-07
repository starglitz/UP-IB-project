package com.example.Clinic.model;

import jdk.jfr.Enabled;

import javax.persistence.*;


@Entity
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double lat;

    private double lng;

    private String name;

    @OneToOne
    private Clinic clinic;




}
