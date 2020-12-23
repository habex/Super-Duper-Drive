package com.udacity.jdnd.data_stores_per.repository;

import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.data.Plant;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
public class PlantRepositoryJPATest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlantRepository_JPA plantRepository;

    @Test
    public void testPriceLessThan() {

        BigDecimal bigDecimal = new BigDecimal(25);

        Plant plant1 = new Plant();
        plant1.setPrice(BigDecimal.valueOf(12));
        Plant plant2 = new Plant();
        plant2.setPrice(BigDecimal.valueOf(20));

        plantRepository.save(plant1);
        plantRepository.save(plant2);

        List<Plant> plants = plantRepository.findByPriceLessThan(bigDecimal);

        Assert.assertEquals(1,plants.size());
        Assertions.assertEquals(plant1.getId(),plants.get(0).getId(),"ID");
    }

    @Test
    public void testDeliveryCompleted() {

        Plant plant1 = new Plant();
        Delivery delivery1 = new Delivery();

        plant1.setDelivery(delivery1);
        delivery1.setPlants(Lists.newArrayList(plant1));

        Assert.assertFalse(plantRepository.existsPlantByIdAndDeliveryCompleted(plant1.getId(),false));
        delivery1.setCompleted(true);
        Assert.assertFalse(plantRepository.existsPlantByIdAndDeliveryCompleted(plant1.getId(),true));

    }
}
