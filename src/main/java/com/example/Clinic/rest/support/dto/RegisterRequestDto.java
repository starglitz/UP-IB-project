package com.example.Clinic.rest.support.dto;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.enumerations.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RegisterRequestDto {

    private Long register_request_id;

    private PatientDto patientDto = new PatientDto();

    private RequestStatus status;

    private boolean visitedMail = false;
}
