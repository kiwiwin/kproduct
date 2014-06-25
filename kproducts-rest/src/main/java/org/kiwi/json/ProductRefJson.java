package org.kiwi.json;

import org.kiwi.domain.Product;

import javax.ws.rs.core.UriInfo;

public class ProductRefJson {
    private final UriInfo uriInfo;
    private Product product;

    public ProductRefJson(UriInfo uriInfo, Product product) {
        this.uriInfo = uriInfo;
        this.product = product;
    }

    public String getName() {
        return product.getName();
    }

    public String getUri() {
        return uriInfo.getBaseUri() + "products/" + product.getId();
    }
}
