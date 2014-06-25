package org.kiwi.json;

import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

import javax.ws.rs.core.UriInfo;

public class PriceRefJson {
    private final Product product;
    private final Price price;
    private final UriInfo uriInfo;


    public PriceRefJson(UriInfo uriInfo, Product product, Price price) {
        this.uriInfo = uriInfo;
        this.product = product;
        this.price = price;
    }

    public int getPrice() {
        return price.getPrice();
    }

    public String getUri() {
        return uriInfo.getBaseUri() + "products/" + product.getId() + "/prices/" + price.getId();
    }

    public String getModifiedBy() {
        return price.getModifiedBy();
    }
}
