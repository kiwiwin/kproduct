package org.kiwi.persistent;

import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;

import javax.inject.Inject;
import java.util.List;

public class MybatisProductRepository implements ProductRepository {
    @Inject
    private ProductMapper productMapper;

    @Inject
    private PriceMapper priceMapper;

    @Override
    public List<Product> all() {
        return productMapper.all();
    }

    @Override
    public Product findProductById(int productId) {
        return productMapper.findProductById(productId);
    }

    @Override
    public Product createProduct(Product product) {
        productMapper.createProduct(product);
        priceMapper.createPrice(product, product.getCurrentPrice());
        return product;
    }

}
