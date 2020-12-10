package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.domain.Delivery;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository2;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
public class DeliveryService2 {

    @Autowired
    DeliveryRepository2 deliveryRepository2;

    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository2.save(delivery);
        return delivery.getId();
    }

    public List<Delivery> findAllDeliveries(){
        return deliveryRepository2.findAll();
    }

    public Delivery findByDeliveryById(Long id){
        return deliveryRepository2.findById(id).get();
    }

    public List<Delivery> findByName(String name){
        return deliveryRepository2.findByName(name);
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.plantPriceSum(deliveryId);
    }
}