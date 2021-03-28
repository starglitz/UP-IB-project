package com.example.Clinic.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Clinic {
    private Long id;
    private String name;
    private String description;
    private List<Doctor> doctors;
    private List<Service> pricelist;
    private float rating;

}
