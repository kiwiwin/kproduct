package org.kiwi.json;

import org.kiwi.domain.Product;

public class CreateProductJson {
    private Product product;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }
}
