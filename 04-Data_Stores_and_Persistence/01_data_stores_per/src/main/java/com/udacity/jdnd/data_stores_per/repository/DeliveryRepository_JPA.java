package com.udacity.jdnd.data_stores_per.repository;

import com.udacity.jdnd.data_stores_per.data.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface DeliveryRepository_JPA extends JpaRepository<Delivery,Long> {
    List<Delivery> findByName(String name);
}
