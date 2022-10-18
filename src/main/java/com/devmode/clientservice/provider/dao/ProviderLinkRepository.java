package com.devmode.clientservice.provider.dao;

import com.devmode.clientservice.provider.model.ProviderLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderLinkRepository extends JpaRepository<ProviderLink, Integer> {
}
