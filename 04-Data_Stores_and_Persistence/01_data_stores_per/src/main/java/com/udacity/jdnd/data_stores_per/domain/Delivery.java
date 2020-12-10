package com.udacity.jdnd.data_stores_per.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.data_stores_per.dataConvertion.Views;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = "Delivery.findByName",
                query = "select d from Delivery d where d.name = :name"),

        @NamedQuery(name = "Delivery.findAll",
                query = "select d from Delivery d")
})

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "address_full", length = 500)
    @JsonView(Views.Public.class)
    private String address;


    @JsonView(Views.Public.class)
    private LocalDateTime deliveryTime;

    @Type(type = "yes_no")
    @JsonView(Views.Public.class)
    private Boolean completed = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

    public Delivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", completed=" + completed +
                ", plants=" + plants +
                '}';
    }
}
