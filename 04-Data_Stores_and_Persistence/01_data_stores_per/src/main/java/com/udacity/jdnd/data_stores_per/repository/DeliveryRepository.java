package com.udacity.jdnd.data_stores_per.repository;

import com.udacity.jdnd.data_stores_per.controller.RecipientAndPrice;
import com.udacity.jdnd.data_stores_per.data.Delivery;
import com.udacity.jdnd.data_stores_per.data.Plant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public List<Delivery> findAll() {
        TypedQuery<Delivery> deliveries = entityManager.createNamedQuery("Delivery.findAll", Delivery.class);
        return deliveries.getResultList();
    }

    public List<Delivery> findDeliveriesByName(String name) {
        TypedQuery<Delivery> deliveries = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        deliveries.setParameter("name", name);
        return deliveries.getResultList();
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        entityManager.remove(entityManager.find(Delivery.class, id));
    }

    public RecipientAndPrice plantPriceSum(Long id) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> criteriaQuery = criteriaBuilder.createQuery(RecipientAndPrice.class);
        Root<Plant> root = criteriaQuery.from(Plant.class);

        Path<String> namePath = root.get("delivery").get("name");
        Path<BigDecimal> pricePath = root.get("price");

        criteriaQuery.select(
                criteriaBuilder.construct(
                        RecipientAndPrice.class,
                        namePath,
                        criteriaBuilder.sum(pricePath)))
                .where(criteriaBuilder.equal(root.get("delivery").get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
