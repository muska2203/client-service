package com.devmode.clientservice.controller;

import com.devmode.clientservice.auth.api.AuthorizedUser;
import com.devmode.clientservice.client.ClientService;
import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String getClients(ModelMap modelMap, @AuthenticationPrincipal AuthorizedUser user) {
        modelMap.addAttribute("clients", clientService.findAllByUserId(user.getLocalId()));
        modelMap.addAttribute("clientCreateRequest", new ClientCreateRequest());
        return "client-list";
    }

    @PostMapping
    public String createClient(@ModelAttribute("clientCreateRequest") ClientCreateRequest createRequest, @AuthenticationPrincipal AuthorizedUser user) {
        try {
            clientService.create(createRequest, user.getLocalId());
            return "redirect:/clients";
        } catch (ConstraintViolationException e) {
            return "errors/error";
        }
    }

    @PostMapping("/{id:\\d+}")
    public String deleteClient(@PathVariable("id") Integer clientId, @AuthenticationPrincipal AuthorizedUser user) {
        try {
            clientService.delete(clientId, user.getLocalId());
            return "redirect:/clients";
        } catch (EntityNotFoundException e) {
            return "errors/error";
        }
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleCommonException(Exception e) {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.setViewName("errors/error");
        return modelAndView;
    }
}
