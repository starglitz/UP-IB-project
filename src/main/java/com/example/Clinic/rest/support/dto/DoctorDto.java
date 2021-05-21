package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DoctorDto {

    private Long id;

    private float grade;

    @NotNull
    private ClinicDto clinic;

    @NotNull
    private UserDto user;
}
