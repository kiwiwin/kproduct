package org.kiwi.json;

import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

public class PriceRefJson {
    private final Product product;
    private final Price price;

    public PriceRefJson(Product product, Price price) {
        this.product = product;
        this.price = price;
    }

    public int getPrice() {
        return price.getPrice();
    }
}
