package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Nurse;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecipeDto {

    private Long recipe_id;

    private boolean validated;

    private Long nurseId;

    private String description;

    private LocalDate issueDate;

    private Long patientBookId;

}
