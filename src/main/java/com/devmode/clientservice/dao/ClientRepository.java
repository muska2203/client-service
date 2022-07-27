package com.devmode.clientservice.dao;

import com.devmode.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAllByUserId(Integer userId);
}
