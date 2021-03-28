package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Service {
    private Long id;
    private String name;
    private int price;
}
