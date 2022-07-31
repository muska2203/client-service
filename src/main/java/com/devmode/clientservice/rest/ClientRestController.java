package com.devmode.clientservice.rest;

import com.devmode.clientservice.auth.api.AuthorizedUser;
import com.devmode.clientservice.client.ClientService;
import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.client.dto.ClientPreview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientPreview>> getByCurrentUser(@AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(clientService.findAllByUserId(user.getLocalId()));
    }

    @PostMapping
    public ResponseEntity<Integer> create(ClientCreateRequest createRequest, @AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(clientService.create(createRequest, user.getLocalId()));
    }
}
