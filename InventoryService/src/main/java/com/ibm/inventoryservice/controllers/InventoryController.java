package com.ibm.inventoryservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.inventoryservice.entities.InventoryItem;
import com.ibm.inventoryservice.repositories.InventoryItemRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class InventoryController {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @GetMapping("/api/inventory/{productCode}")
    @HystrixCommand
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
        if(inventoryItem.isPresent()) {
            return new ResponseEntity(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/inventory")
    @HystrixCommand
    public List<InventoryItem> getInventory() {
        return inventoryItemRepository.findAll();
    }
}
