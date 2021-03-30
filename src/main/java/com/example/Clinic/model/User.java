package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "country is mandatory")
    private String country;

    @NotBlank(message = "phone number is mandatory")
    private String phoneNumber;

    public User(String email, String password, String name, String lastName,
                String address, String city, String country, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }
}
