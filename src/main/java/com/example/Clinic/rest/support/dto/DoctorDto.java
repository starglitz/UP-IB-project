package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class DoctorDto {

    private Long id;

    private float grade;

    @NotNull
    private ClinicDto clinic;

    @NotNull
    private UserDto user;
}
