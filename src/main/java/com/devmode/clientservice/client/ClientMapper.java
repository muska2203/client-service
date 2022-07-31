package com.devmode.clientservice.client;

import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.client.dto.ClientPreview;
import com.devmode.clientservice.client.model.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientPreview map(Client source);

    Client map(ClientCreateRequest source);
}
