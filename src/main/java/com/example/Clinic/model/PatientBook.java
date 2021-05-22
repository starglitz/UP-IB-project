package com.example.Clinic.model;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class PatientBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_book_id")
    private Long id;

    @Nullable
    @ManyToOne
    private Patient patient;
}
