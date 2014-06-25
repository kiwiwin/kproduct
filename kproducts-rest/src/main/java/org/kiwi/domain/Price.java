package org.kiwi.domain;

public class Price {
    private int price;
    private int id;

    public Price(int price) {
        this.price = price;
    }

    public Price(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
