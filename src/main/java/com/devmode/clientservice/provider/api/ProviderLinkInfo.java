package com.devmode.clientservice.provider.api;

import com.devmode.clientservice.provider.model.ProviderLinkTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderLinkInfo {

    private Integer id;

    private ProviderLinkTypes type;

    private String link;
}
