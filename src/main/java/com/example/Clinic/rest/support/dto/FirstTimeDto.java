package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FirstTimeDto {
    boolean firstTime;

    public FirstTimeDto(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
