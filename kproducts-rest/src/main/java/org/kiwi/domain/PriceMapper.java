package org.kiwi.domain;

import java.util.List;

public interface PriceMapper {
    public List<Price> getProductPrices(Product productId);

    Price getPrice(Product product, int priceId);

    Price createPrice(Product product, Price price);
}
