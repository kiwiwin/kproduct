package org.kiwi.domain;

import java.util.List;

public interface PriceMapper {
    public List<Price> getProductPrices(Product productId);
}
