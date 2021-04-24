package com.example.Clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Patient extends User {

    @NotEmpty(message = "last name is mandatory")
    @NotBlank(message = "cant be blank")
    @NotNull(message = "cant be null")
    private String lbo;
    private boolean enabled = true;
    private boolean approved = false;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "register_request_id")
//    private RegisterRequest register_request;

    public Patient( String email, String password, String name, String lastName,
                    String address, String city, String country, String phoneNumber,
                    String lbo) {
        super(email, password, name, lastName, address, city, country, phoneNumber);
        this.lbo = lbo;
        this.enabled = true;
    }
}
