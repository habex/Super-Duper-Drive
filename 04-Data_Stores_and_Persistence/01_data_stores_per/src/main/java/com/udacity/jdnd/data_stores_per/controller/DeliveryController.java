package com.udacity.jdnd.data_stores_per.controller;

import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    //@GetMapping
    public List<Delivery> findAllDeliveries(){
        return deliveryService.findAllDeliveries();
    }

    //@GetMapping
    public Delivery findByDeliveryById(@RequestParam Long id) {
        return deliveryService.findByDeliveryById(id);
    }

    @GetMapping
    public List<Delivery> findByName(@RequestParam String name) {
        return deliveryService.findByName(name);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }

}
