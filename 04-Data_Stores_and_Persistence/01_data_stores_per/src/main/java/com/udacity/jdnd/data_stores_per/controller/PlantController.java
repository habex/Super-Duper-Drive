package com.udacity.jdnd.data_stores_per.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.data_stores_per.domain.Plant;
import com.udacity.jdnd.data_stores_per.dataConvertion.PlantDTO;
import com.udacity.jdnd.data_stores_per.dataConvertion.Views;
import com.udacity.jdnd.data_stores_per.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @PostMapping
    public Long save(Plant plant){
        return plantService.save(plant);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<Plant> getPlants() {
        return plantService.findAll();
    }

    @GetMapping("/priceLessThan/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
        return plantService.findPlantsBelowPrice(price);
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.delivered(id);
    }

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
