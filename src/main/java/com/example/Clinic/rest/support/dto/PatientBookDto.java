package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientBookDto {

    private Long id;

    private PatientDto patientDto;
}
