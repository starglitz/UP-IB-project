package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long service_id;
    private String name;
    private int price;
    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;
}
