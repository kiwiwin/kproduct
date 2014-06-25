package org.kiwi.resource;

import org.kiwi.domain.PriceMapper;
import org.kiwi.domain.Product;
import org.kiwi.json.PriceRefJson;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

public class PricesResource {
    private final Product product;
    private final PriceMapper priceMapper;

    public PricesResource(Product product, PriceMapper priceMapper) {
        this.product = product;
        this.priceMapper = priceMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceRefJson> getAllPricesOfProduct() {
        return priceMapper.getProductPrices(product).stream()
                .map(price -> new PriceRefJson(product, price))
                .collect(Collectors.toList());
    }
}
