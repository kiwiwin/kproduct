package org.kiwi.json;

import org.kiwi.domain.Price;

import java.sql.Timestamp;

public class CreatePriceJson {
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public Price getPrice() {
        return new Price(price, "kiwi", new Timestamp(114, 1, 1, 0, 0, 0, 0));
    }
}
