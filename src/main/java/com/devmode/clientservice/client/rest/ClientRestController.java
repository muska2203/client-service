package com.devmode.clientservice.client.rest;

import com.devmode.clientservice.auth.api.AuthorizedUser;
import com.devmode.clientservice.client.ClientService;
import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.client.dto.ClientPreview;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientPreview>> getByCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(clientService.findAllByUserId(user.getLocalId()));
    }

    @PostMapping
    public ResponseEntity<Integer> create(ClientCreateRequest createRequest, @Parameter(hidden = true) @AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(clientService.create(createRequest, user.getLocalId()));
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id, @Parameter(hidden = true) @AuthenticationPrincipal AuthorizedUser user) {
        clientService.delete(id, user.getLocalId());
        return ResponseEntity.ok().build();
    }
}
