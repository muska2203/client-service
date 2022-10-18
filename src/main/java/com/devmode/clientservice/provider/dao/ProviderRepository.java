package com.devmode.clientservice.provider.dao;

import com.devmode.clientservice.provider.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider getByNickname(String username);
}
