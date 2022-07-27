package com.devmode.clientservice.controller;

import com.devmode.clientservice.auth.api.AuthorizedUser;
import com.devmode.clientservice.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class HTMLController {

    private final UserService userService;

    @GetMapping("/hello")
    public String test(ModelMap modelMap, @AuthenticationPrincipal AuthorizedUser user) {
        modelMap.addAttribute("user", userService.getByExternalId(user.getName()));
        return "hello";
    }

    @GetMapping
    public String test() {
        return "index";
    }
}
