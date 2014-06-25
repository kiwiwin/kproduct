package org.kiwi.domain;

public class PriceWithId {
    private final int id;
    private final Price price;

    private PriceWithId(int id, Price price) {
        this.id = id;
        this.price = price;
    }

    public static Price priceWithId(int id, Price price) {
        return new PriceWithId(id, price).getPrice();
    }

    public Price getPrice() {
        price.id = id;
        return price;
    }
}
