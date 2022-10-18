package com.devmode.clientservice.provider;

import com.devmode.clientservice.provider.api.ProviderInfo;
import com.devmode.clientservice.provider.api.ProviderRequest;
import com.devmode.clientservice.provider.dao.ProviderRepository;
import com.devmode.clientservice.provider.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    @Transactional
    public Integer create(ProviderRequest createRequest) {
        Provider provider = providerMapper.mapToModel(createRequest);
        Provider savedProvider = providerRepository.save(provider);
        return savedProvider.getId();
    }

    @Transactional
    public void update(ProviderRequest updateRequest) {
        Provider provider = providerRepository.getById(updateRequest.getId());
        providerMapper.update(updateRequest, provider);
        providerRepository.save(provider);
    }

    @Transactional(readOnly = true)
    public ProviderInfo getByNickname(String nickname) {
        Provider provider = providerRepository.getByNickname(nickname);
        return providerMapper.map(provider);
    }
}
