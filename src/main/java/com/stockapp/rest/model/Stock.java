package com.stockapp.rest.model;

import java.sql.Timestamp;
import java.util.Date;

public class Stock {
    private int id;
    private String name;
    private double currentPrice;
    private Timestamp lastUpdate;

    public Stock(int id, String name, double currentPrice, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String toString() {
        return "Stock [id=" + id + ", Name=" + name + ", CurrentPrice=" + currentPrice + ", LastUpdate=" + new Date(lastUpdate.getTime()) + "]";
    }
}
