package org.kiwi.persistent;

import org.apache.ibatis.annotations.Param;
import org.kiwi.domain.Product;

import java.util.List;

public interface ProductMapper {
    int createProduct(@Param("product") Product product);

    List<Product> all();
}
