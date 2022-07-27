package com.devmode.clientservice.controller;

import com.devmode.clientservice.auth.api.AuthorizedUser;
import com.devmode.clientservice.model.Client;
import com.devmode.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String getClients(ModelMap modelMap, @AuthenticationPrincipal AuthorizedUser user) {
        List<Client> clients = clientService.findAllByUserId(user.getLocalId());
        modelMap.addAttribute("clients", clients);
        return "client-list";
    }
}
