package com.example.Clinic.rest.support.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class UserDto {
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank
    private String phoneNumber;
}
