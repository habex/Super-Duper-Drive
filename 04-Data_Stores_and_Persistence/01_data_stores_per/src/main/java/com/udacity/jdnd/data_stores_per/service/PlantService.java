package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.data.Plant;
import com.udacity.jdnd.data_stores_per.repository.PlantRepository_JPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    PlantRepository_JPA plantRepository;

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public Plant findById(Long id){
        return plantRepository.findById(id).get();
    }

    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    public Boolean delivered(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id,true);
       //return plantRepository.deliveryCompleted(id);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
    }

    public Plant getPlantByName(String name) {
        return plantRepository.findByName(name);
    }
}