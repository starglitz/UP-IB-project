package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NurseDto {

    private Long id;

    private ClinicDto clinicDto;

    private UserDto userDto;
}
