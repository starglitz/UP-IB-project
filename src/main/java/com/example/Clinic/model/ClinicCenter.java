package com.example.Clinic.model;


import lombok.Data;

import java.util.List;
@Data
public class ClinicCenter {
    private Long id;
    private String name;
    private String address;
    private List<Clinic> clinics;
}
