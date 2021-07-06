package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DrugChangeDto {
    @NotBlank
    private String oldDrug;
    @NotBlank
    private String newDrug;
}
