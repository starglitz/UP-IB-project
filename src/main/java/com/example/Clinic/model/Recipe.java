package com.example.Clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recipe_id")
    private Long recipe_id;

    private Boolean validated;

    @ManyToOne
    @NotNull
    private Nurse nurse;

    private String description;
    private LocalDate issueDate;

    @Column(name = "patient_book_id")
    private Long patientBookId;

    public Recipe(Long recipe_id, boolean b, Nurse nurse, String description, LocalDate issueDate) {
        this.recipe_id = recipe_id;
        this.validated = b;
        this.nurse = nurse;
        this.description = description;
        this.issueDate = issueDate;
    }
}
