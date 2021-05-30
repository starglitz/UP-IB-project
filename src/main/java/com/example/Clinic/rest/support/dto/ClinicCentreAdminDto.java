package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClinicCentreAdminDto {

    private Long id;

    @NotNull
    private ClinicCentreDto clinicCenter;

    @NotNull
    private UserDto user;
}
