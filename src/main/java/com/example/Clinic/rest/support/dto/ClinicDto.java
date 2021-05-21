package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ClinicDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private List<DoctorDto> doctors;

    @NotNull
    private List<ServiceDto> services;

    private float rating;

    @NotNull
    private AddressDto address;

    @NotBlank
    private String addressName;

    @NotBlank
    private String lat;

    @NotBlank
    private String lng;
}
