package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Doctor extends User {
    private float grade;
}
