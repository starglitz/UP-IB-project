package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient extends User {
    private String id;
    private boolean enabled;
}
