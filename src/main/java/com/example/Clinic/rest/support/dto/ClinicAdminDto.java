package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClinicAdminDto {

    private Long id;

    @NotNull
    private ClinicDto clinic;

    private UserDto user;
}
