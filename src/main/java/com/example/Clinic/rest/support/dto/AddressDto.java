package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddressDto {

    private Long id;

    private double lat;

    private double lng;

    @NotBlank
    private String name;

    @NotNull
    private ClinicDto clinic;
}
