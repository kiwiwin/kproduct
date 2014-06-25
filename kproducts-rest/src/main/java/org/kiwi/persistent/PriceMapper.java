package org.kiwi.persistent;

import org.apache.ibatis.annotations.Param;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

import java.util.List;

public interface PriceMapper {
    public List<Price> getProductPrices(Product productId);

    Price getPrice(Product product, int priceId);

    int createPrice(@Param("product") Product product, @Param("price") Price price);
}
