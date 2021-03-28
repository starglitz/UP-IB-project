package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicAdmin extends User{
    private Clinic clinic;

}
