package org.kiwi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/products")
public class ProductsResource {
    @GET
    public String getAllProducts() {
        return "";
    }
}
