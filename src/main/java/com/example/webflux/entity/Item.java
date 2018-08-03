package com.example.webflux.entity;

public class Item {

    private String id;
    private String description;
    private Double price;

    public Item(String id, String description, Double price) {
        super();
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
