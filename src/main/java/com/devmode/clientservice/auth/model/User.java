package com.devmode.clientservice.auth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String externalId;

    @Column
    private String name;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private String email;

}
