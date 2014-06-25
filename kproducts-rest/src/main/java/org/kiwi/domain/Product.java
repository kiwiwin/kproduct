package org.kiwi.domain;

public class Product {
    int id;

    private String name;
    private String description;

    public Product(String name) {
        this.name = name;
    }

    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
