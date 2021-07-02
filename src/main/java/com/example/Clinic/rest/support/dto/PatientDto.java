package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PatientDto {

    private Long id;

    @NotBlank
    private String lbo;

    private boolean enabled = true;

    private boolean approved = false;

    private Long patient_book_id;

    private UserDto userDto;
}
