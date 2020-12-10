package com.udacity.jdnd.data_stores_per.domain;

import javax.persistence.*;

@Entity
public class Flower extends Plant{

    private String color;

    public Flower() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color='" + color + '\'' +
                '}';
    }
}
