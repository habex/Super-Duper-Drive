package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public List<Delivery> findAllDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery findByDeliveryById(Long id){
        return deliveryRepository.find(id);
    }

    public List<Delivery> findByName(String name){
        return deliveryRepository.findDeliveriesByName(name);
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.plantPriceSum(deliveryId);
    }
}