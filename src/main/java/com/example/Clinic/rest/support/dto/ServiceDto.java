package com.example.Clinic.rest.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceDto {

    private Long service_id;

    private String name;

    private int price;

    private ClinicDto clinicDto;

}
