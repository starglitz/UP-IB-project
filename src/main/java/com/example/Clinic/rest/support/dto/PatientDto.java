package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {

    private Long id;

    private String lbo;

    private boolean enabled = true;

    private boolean approved = false;

    private long patientBookId;

    private UserDto userDto;
}
