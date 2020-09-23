package com.udacity.jdnd.course1.data;

import java.sql.Timestamp;

public class Delivery {
    private Integer id;
    private Integer orderId;
    private Timestamp time;

    /* getters and setters not shown */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
