package com.udacity.jdnd.data_stores_per.dao;

import com.udacity.jdnd.data_stores_per.data.CandyData;

import java.util.List;

public interface CandyDAO<T> {

    List<CandyData> list();
    void addToDelivery(Long candyId, Long deliveryid);
    List<CandyData> findByDelivery(Long deliveryId);

}
