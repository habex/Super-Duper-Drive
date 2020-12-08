package com.udacity.jdnd.data_stores_per.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.data_stores_per.data.Plant;
import com.udacity.jdnd.data_stores_per.dataConvertion.PlantDTO;
import com.udacity.jdnd.data_stores_per.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name) {
        Plant plant = plantService.getPlantByName(name);
        return convertPlantToPlantDTO(plant);
    }

    @JsonView(RecipientAndPrice.class)
    public Plant getFilteredPlant(String name) {
        return plantService.getPlantByName(name);
    }

    private PlantDTO convertPlantToPlantDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

}
