package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipe_id;
    private Boolean validated;

    @ManyToOne
    private Nurse nurse;

    private String description;
    private LocalDate issueDate;

    public Recipe(Long recipe_id, boolean b, Nurse nurse, String description, LocalDate issueDate) {
        this.recipe_id = recipe_id;
        this.validated = b;
        this.nurse = nurse;
        this.description = description;
        this.issueDate = issueDate;
    }
}
