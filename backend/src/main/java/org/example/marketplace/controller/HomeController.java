package org.example.marketplace.controller;

import org.example.marketplace.models.Item;
import org.example.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class HomeController {
    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Item> itemList() {
        List<Item> items = itemService.findAll();

        System.out.println("Количество товаров: " + items.size());

        return itemService.findAll();
    }
}
