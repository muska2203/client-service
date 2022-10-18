package com.devmode.clientservice.controller;

import com.devmode.clientservice.provider.ProviderService;
import com.devmode.clientservice.provider.api.ProviderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/{nickname:[\\w-_]+}")
    public String getProviderByNickname(ModelMap modelMap, @PathVariable String nickname) {
        ProviderInfo providerInfo = providerService.getByNickname(nickname);
        if (providerInfo == null) {
            return "errors/error";
        }
        modelMap.addAttribute("providerInfo", providerInfo);
        return "provider";
    }
}
