package org.kiwi.json;

import org.kiwi.domain.Price;

public class CreatePriceJson {
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public Price getPrice() {
        return new Price(price, "kiwi");
    }
}
