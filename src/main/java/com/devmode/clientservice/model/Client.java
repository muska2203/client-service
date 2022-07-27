package com.devmode.clientservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Client {

    @Id
    private Integer id;

    private Integer userId;

    private String name;

    private String phoneNumber;
}
