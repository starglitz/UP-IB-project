package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterNurseDto {

    private Long id;

    private ClinicDto clinic;

    private UserRegisterDto user;
}
