package com.example.Clinic.model;

import com.example.Clinic.model.enumerations.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String lastName;

    private String address;

    private String city;

    private String country;

    private String phoneNumber;

    private boolean firstTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> roles = new HashSet<>();

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    private boolean enabled;

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

    public User(String email, String password, String name, String lastName,
                String address, String city, String country, String phoneNumber, boolean enabled) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
    }


    public User(Long id, String email, String password, String name,
                String lastName, String address, String city, String country,
                String phoneNumber) {
        this.id = id;
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
