package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class IllnessChangeDto {

    private Long doctorId;
    @NotBlank
    private String oldIllness;
    @NotBlank
    private String newIllness;
}
