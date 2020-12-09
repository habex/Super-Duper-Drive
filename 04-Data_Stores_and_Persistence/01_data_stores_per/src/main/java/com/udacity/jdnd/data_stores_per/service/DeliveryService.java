package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.domain.Delivery;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryRepository1 deliveryRepository1;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.save(delivery);
        return delivery.getId();
    }

    public List<Delivery> findAllDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery findByDeliveryById(Long id){
        return deliveryRepository.findById(id).get();
    }

    public List<Delivery> findByName(String name){
        return deliveryRepository.findByName(name);
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository1.plantPriceSum(deliveryId);
    }
}