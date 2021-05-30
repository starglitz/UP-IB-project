package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegisterClinicAdminDto {

    private Long id;

    @NotNull
    private ClinicDto clinic;

    private UserRegisterDto user;
}
