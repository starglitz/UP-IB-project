package com.example.Clinic.model;

import com.example.Clinic.model.enumerations.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastName;

    private String address;

    private String city;

    private String country;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<UserRole> userRole;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

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
