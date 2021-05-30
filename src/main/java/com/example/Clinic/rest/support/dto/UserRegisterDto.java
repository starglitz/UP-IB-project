package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserRegisterDto {private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    private String password;

    private String passwordValidate;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank
    private String phoneNumber;

    private boolean enabled = true;
}
