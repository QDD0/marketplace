package org.example.marketplace.controller;

import org.example.marketplace.models.Client;
import org.example.marketplace.models.Item;
import org.example.marketplace.service.ClientService;
import org.example.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ClientService clientService;
    private final ItemService itemService;

    @Autowired
    public ProfileController(ClientService clientService, ItemService itemService) {
        this.clientService = clientService;
        this.itemService = itemService;
    }

    @GetMapping("/personal")
    public List<Client> personal() {
        return clientService.findAll();
    }
}
