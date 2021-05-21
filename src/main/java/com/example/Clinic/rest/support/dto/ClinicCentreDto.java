package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Clinic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClinicCentreDto {

    private Long clinic_center_id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private List<ClinicDto> clinics;
}
