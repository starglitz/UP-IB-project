package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegisterDoctorDto {
    private Long id;

    private float grade;

    private ClinicDto clinic;

    @NotNull
    private UserRegisterDto user;
}
