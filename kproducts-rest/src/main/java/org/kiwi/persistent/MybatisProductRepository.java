package org.kiwi.persistent;

import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;

import java.util.List;

public class MybatisProductRepository implements ProductRepository {
    @Override
    public List<Product> all() {
        return null;
    }

    @Override
    public Product findProductById(int productId) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
