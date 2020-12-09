package com.udacity.jdnd.data_stores_per.repository;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.domain.Delivery;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findByName(String name);
}
