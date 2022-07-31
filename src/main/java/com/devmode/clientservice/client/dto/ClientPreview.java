package com.devmode.clientservice.client.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ClientPreview {

    @Id
    private Integer id;

    private String name;

    private String phoneNumber;
}
