package com.udacity.jdnd.data_stores_per.dao;


import com.udacity.jdnd.data_stores_per.data.CandyData;

import java.util.*;
import java.util.stream.Collectors;

public class CandyDAOImp implements CandyDAO<CandyData> {

    private List<CandyData> candyDataList = new ArrayList<>();

    @Override
    public Optional<CandyData> get(Long id) {
        return Optional.ofNullable(candyDataList.get(id.intValue()));
    }

    @Override
    public Collection<CandyData> getAll() {
        return candyDataList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }


    @Override
    public Long save(CandyData candyData) {
        candyDataList.add(candyData);
        Long index = Long.valueOf(candyDataList.size() - 1);
        candyData.setId(index);
        return index;
    }

    @Override
    public void update(CandyData candyData) {
        candyDataList.set(candyData.getId().intValue(),candyData);
    }

    @Override
    public void delete(CandyData candyData) {
        candyDataList.set(candyData.getId().intValue(),null);
    }

    @Override
    public void addCandyToDelivery(Long candyId, Long deliveryId) {

    }

    @Override
    public List<CandyData> findByDelivery(Long deliveryId) {
        return null;
    }
}
