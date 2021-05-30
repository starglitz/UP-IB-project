package com.example.Clinic.model;

import com.example.Clinic.model.enumerations.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "register_request")
public class RegisterRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long register_request_id;

    @OneToOne
    private Patient patient = new Patient();
    private RequestStatus status;
    private boolean visitedMail = false;

    public RegisterRequest(Patient patient, RequestStatus status, boolean visitedMail) {
        this.patient = patient;
        this.status = status;
        this.visitedMail = visitedMail;
    }
}
