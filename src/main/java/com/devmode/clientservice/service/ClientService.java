package com.devmode.clientservice.service;

import com.devmode.clientservice.dao.ClientRepository;
import com.devmode.clientservice.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public List<Client> findAllByUserId(Integer userId) {
        return clientRepository.findAllByUserId(userId);
    }
}
