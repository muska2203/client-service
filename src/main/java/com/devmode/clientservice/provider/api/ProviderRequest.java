package com.devmode.clientservice.provider.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProviderRequest {

    private Integer id;

    private Integer userId;

    private String nickname;

    private String phoneNumber;

    private List<ProviderLinkInfo> links;
}
