package org.kiwi.domain;

import java.security.Timestamp;

public class Product {
    int id;

    private String name;
    private String description;
    private Price currentPrice;

    public Product() {

    }


    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }
}
