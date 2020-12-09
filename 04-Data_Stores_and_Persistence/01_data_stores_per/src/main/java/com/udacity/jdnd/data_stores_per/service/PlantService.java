package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.domain.Plant;
import com.udacity.jdnd.data_stores_per.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    public Boolean delivered(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id,true);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
    }

    public Plant getPlantByName(String name) {
        return plantRepository.findByName(name);
    }
}