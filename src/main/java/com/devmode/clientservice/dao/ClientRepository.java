package com.devmode.clientservice.dao;

import com.devmode.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Integer> {
}
