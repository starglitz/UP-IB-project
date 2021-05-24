package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PatientRegisterDto {
    private Long id;

    @NotBlank
    private String lbo;

    private boolean enabled = true;

    private boolean approved = false;

//    private PatientBookDto patientBookDto;

    private UserRegisterDto userDto;
}
