package org.kiwi.domain;

public class ProductWithId {
    private final int id;
    private final Product product;

    private ProductWithId(int id, Product product) {
        this.id = id;
        this.product = product;
    }

    public static Product productWithId(int id, Product product) {
        return new ProductWithId(id, product).getProduct();
    }

    public Product getProduct() {
        product.id = id;
        return product;
    }
}
