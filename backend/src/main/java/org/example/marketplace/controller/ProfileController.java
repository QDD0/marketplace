package org.example.marketplace.controller;

import org.example.marketplace.models.Client;
import org.example.marketplace.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ClientService clientService;

    @Autowired
    public ProfileController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/personal")
    public List<Client> personal() {
        return clientService.findAll();
    }
}
