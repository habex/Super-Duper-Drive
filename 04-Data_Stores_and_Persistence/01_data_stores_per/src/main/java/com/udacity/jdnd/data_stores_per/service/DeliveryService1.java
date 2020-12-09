package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.domain.Delivery;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService1 {

    @Autowired
    DeliveryRepository1 deliveryRepository1;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository1.persist(delivery);
        return delivery.getId();
    }

    public List<Delivery> findAllDeliveries(){
        return deliveryRepository1.findAll();
    }

    public Delivery findByDeliveryById(Long id){
        return deliveryRepository1.find(id);
    }

    public List<Delivery> findByName(String name){
        return deliveryRepository1.findDeliveriesByName(name);
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository1.plantPriceSum(deliveryId);
    }
}