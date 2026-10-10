package org.example.marketplace.service;

import org.example.marketplace.models.Item;
import org.example.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository repository) {
        itemRepository = repository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
