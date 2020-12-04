package com.udacity.jdnd.data_stores_per.service;

import com.udacity.jdnd.data_stores_per.data.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}
