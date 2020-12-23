package com.udacity.jdnd.data_stores_per.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.data_stores_per.dataConvertion.Views;
import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.service.DeliveryService_JPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/delivery")
public class DeliveryController_JPA {

    @Autowired
    DeliveryService_JPA deliveryServiceJPA;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryServiceJPA.save(delivery);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<Delivery> findAllDeliveries(){
        return deliveryServiceJPA.findAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery findByDeliveryById(@PathVariable Long id) {
        return deliveryServiceJPA.findByDeliveryById(id);
    }

    @GetMapping("/name/{name}")
    @JsonView(Views.Public.class)
    public List<Delivery> findByName(@PathVariable String name) {
        return deliveryServiceJPA.findByName(name);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryServiceJPA.getBill(deliveryId);
    }

}
