package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TokenExpiration {
    private String token;
    private LocalDateTime time;
}
