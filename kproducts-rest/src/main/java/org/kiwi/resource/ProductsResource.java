package org.kiwi.resource;

import org.kiwi.domain.PriceMapper;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.json.CreateProductJson;
import org.kiwi.json.ProductRefJson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private PriceMapper priceMapper;

    @Path("{productId}/prices")
    public PricesResource getPriceOfProduct(@PathParam("productId") int productId) {
        return new PricesResource(productRepository.findProductById(productId), priceMapper);
    }

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(CreateProductJson createProductJson, @Context UriInfo uriInfo) {
        final Product newProduct = productRepository.createProduct(createProductJson.getProduct());
        return Response.status(201).header("location", new ProductRefJson(uriInfo, newProduct).getUri()).build();
    }
}
