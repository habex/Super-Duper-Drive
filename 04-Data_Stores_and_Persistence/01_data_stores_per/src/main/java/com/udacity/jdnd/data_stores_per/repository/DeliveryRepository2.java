package com.udacity.jdnd.data_stores_per.repository;

import com.udacity.jdnd.data_stores_per.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface DeliveryRepository2 extends JpaRepository<Delivery,Long> {
    List<Delivery> findByName(String name);
}
