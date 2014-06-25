package org.kiwi.domain;

public class Price {
    int id;
    private int price;
    private String modifiedBy;

    public Price(int price, String modifiedBy) {
        this.price = price;
        this.modifiedBy = modifiedBy;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
}
