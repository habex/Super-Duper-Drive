package com.udacity.jdnd.data_stores_per.dao;

import java.util.List;

public interface CandyDAO<T> extends DAO<T>{
    void addCandyToDelivery(Long candyId, Long deliveryId);
    List<T> findByDelivery(Long deliveryId);
}
