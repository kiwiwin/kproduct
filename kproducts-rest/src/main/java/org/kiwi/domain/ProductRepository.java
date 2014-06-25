package org.kiwi.domain;

import java.util.List;

public interface ProductRepository {
    List<Product> all();

    Product findProductById(int productId);

    Product createProduct(Product product);

}
