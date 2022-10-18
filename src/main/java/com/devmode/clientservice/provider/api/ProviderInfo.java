package com.devmode.clientservice.provider.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProviderInfo {

    private Integer id;

    private String nickname;

    private String phoneNumber;

    private String name;

    private List<ProviderLinkInfo> links;
}
