package com.udacity.jdnd.data_stores_per.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.data_stores_per.dataConvertion.Views;
import com.udacity.jdnd.data_stores_per.domain.Delivery;
import com.udacity.jdnd.data_stores_per.service.DeliveryService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/delivery")
public class DeliveryController1 {

    @Autowired
    DeliveryService1 deliveryService1;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService1.save(delivery);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<Delivery> findAllDeliveries(){
        return deliveryService1.findAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery findByDeliveryById(@PathVariable Long id) {
        return deliveryService1.findByDeliveryById(id);
    }

    @GetMapping("/name/{name}")
    @JsonView(Views.Public.class)
    public List<Delivery> findByName(@PathVariable String name) {
        return deliveryService1.findByName(name);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService1.getBill(deliveryId);
    }

}
