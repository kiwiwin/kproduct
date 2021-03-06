package org.kiwi.resource;

import org.kiwi.domain.Price;
import org.kiwi.persistent.PriceMapper;
import org.kiwi.domain.Product;
import org.kiwi.json.CreatePriceJson;
import org.kiwi.json.PriceRefJson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    public List<PriceRefJson> getAllPricesOfProduct(@Context UriInfo uriInfo) {
        return priceMapper.getProductPrices(product).stream()
                .map(price -> new PriceRefJson(uriInfo, product, price))
                .collect(Collectors.toList());
    }

    @GET
    @Path("{priceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PriceRefJson getPrice(@PathParam("priceId") int priceId, @Context UriInfo uriInfo) {
        return new PriceRefJson(uriInfo, product, priceMapper.getPrice(product, priceId));
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrice(CreatePriceJson createPriceJson, @Context UriInfo uriInfo) {
        final Price newPrice = createPriceJson.getPrice();
        priceMapper.createPrice(product, newPrice);
        return Response.status(201).header("location", new PriceRefJson(uriInfo, product, newPrice).getUri()).build();
    }
}
