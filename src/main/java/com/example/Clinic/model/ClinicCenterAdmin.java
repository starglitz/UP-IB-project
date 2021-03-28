package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicCenterAdmin extends User{
    private ClinicCenter clinicCenter;
}
