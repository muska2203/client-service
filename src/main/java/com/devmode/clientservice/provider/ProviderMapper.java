package com.devmode.clientservice.provider;

import com.devmode.clientservice.provider.api.ProviderInfo;
import com.devmode.clientservice.provider.api.ProviderLinkInfo;
import com.devmode.clientservice.provider.api.ProviderRequest;
import com.devmode.clientservice.provider.model.Provider;
import com.devmode.clientservice.provider.model.ProviderLink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProviderMapper {

    Provider mapToModel(ProviderRequest source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "provider", ignore = true)
    ProviderLink mapToModel(ProviderLinkInfo source);

    @Mapping(target = "id", ignore = true)
    void update(ProviderRequest source, @MappingTarget Provider target);

    ProviderInfo map(Provider source);

    ProviderLinkInfo map(ProviderLink source);
}
