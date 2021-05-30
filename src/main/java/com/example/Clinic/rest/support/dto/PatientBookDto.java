package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PatientBookDto {

    private Long id;

    private PatientDto patientDto;

    List<String> illnessHistory;

    List<String> drugs;

    private String xml;

    public PatientBookDto(Long id, PatientDto patientDto, List<String> illnessHistory,
                          List<String> drugs, String xml) {
        this.id = id;
        this.patientDto = patientDto;
        this.illnessHistory = illnessHistory;
        this.drugs = drugs;
        this.xml = xml;
    }
}
