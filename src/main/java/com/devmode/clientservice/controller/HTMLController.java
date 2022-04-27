package com.devmode.clientservice.controller;

import com.devmode.clientservice.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class HTMLController {

    private final UserService userService;

    @GetMapping
    public String test(ModelMap modelMap, @AuthenticationPrincipal OidcUser user) {
        modelMap.addAttribute("user", userService.getByExternalId(user.getName()));
        return "index";
    }
}
