package org.kiwi.resource;

import org.kiwi.domain.ProductRepository;
import org.kiwi.json.ProductRefJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductRefJson> getAllProducts(@Context UriInfo uriInfo) {
        return productRepository.all().stream()
                .map(product -> new ProductRefJson(uriInfo, product))
                .collect(Collectors.toList());
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRefJson getProduct(@PathParam("productId") int productId, @Context UriInfo uriInfo) {
        return new ProductRefJson(uriInfo, productRepository.findProductById(productId));
    }
}
