package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ClinicAdminDto {

    private Long id;

    @NotNull
    private ClinicDto clinic;

    private UserDto user;
}
