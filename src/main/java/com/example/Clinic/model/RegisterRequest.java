package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class RegisterRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long register_request_id;

    @OneToOne
    private Patient patient;
    private RequestStatus status;
    private boolean visitedMail;



}
