package org.kiwi.json;

import org.kiwi.domain.Product;

public class ProductRefJson {
    private Product product;

    public ProductRefJson(Product product) {
        this.product = product;
    }

    public String getName() {
        return product.getName();
    }
}
