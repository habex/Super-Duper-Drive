package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository_JPA;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
public class DeliveryService_JPA {

    @Autowired
    DeliveryRepository_JPA deliveryRepositoryJPA;

    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepositoryJPA.save(delivery);
        return delivery.getId();
    }

    public List<Delivery> findAllDeliveries(){
        return deliveryRepositoryJPA.findAll();
    }

    public Delivery findByDeliveryById(Long id){
        return deliveryRepositoryJPA.findById(id).get();
    }

    public List<Delivery> findByName(String name){
        return deliveryRepositoryJPA.findByName(name);
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.plantPriceSum(deliveryId);
    }
}