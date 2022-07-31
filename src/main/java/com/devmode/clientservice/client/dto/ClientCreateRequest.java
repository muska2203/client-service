package com.devmode.clientservice.client.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClientCreateRequest {

    @NotNull
    @Size(min = 1, max = 150)
    private String name;

    @NotNull
    @Size(min = 1, max = 150)
    private String phoneNumber;
}
