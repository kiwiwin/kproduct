package org.kiwi.resource;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.*;
import org.kiwi.persistent.PriceMapper;
import org.kiwi.resource.exception.ResourceNotFoundException;
import org.kiwi.resource.exception.ProductNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.kiwi.domain.PriceWithId.priceWithId;
import static org.kiwi.domain.ProductWithId.productWithId;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesResourceTest extends JerseyTest {
    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private PriceMapper mockPriceMapper;

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi.resource")
                .register(ProductNotFoundExceptionHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(mockProductRepository).to(ProductRepository.class);
                        bind(mockPriceMapper).to(PriceMapper.class);
                    }
                });
    }

    @Test
    public void should_get_all_prices_of_a_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(productWithId(1, new Product("first", "good")));
        when(mockPriceMapper.getProductPrices(anyObject())).thenReturn(Arrays.asList(priceWithId(1, new Price(120, "kiwi", new Timestamp(114, 1, 1, 0, 0, 0, 0))), priceWithId(2, new Price(200, "kiwi", new Timestamp(114, 1, 1, 0, 0, 0, 0)))));

        final Response response = target("/products/1/prices")
                .request()
                .get();


        assertThat(response.getStatus(), is(200));

        final List prices = response.readEntity(List.class);
        assertThat(prices.size(), is(2));

        final Map price = (Map) prices.get(0);
        assertThat(price.get("price"), is(120));
        assertThat((String) price.get("uri"), endsWith("products/1/prices/1"));
    }

    @Test
    public void should_get_price_of_a_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(productWithId(1, new Product("first", "good")));
        when(mockPriceMapper.getPrice(anyObject(), eq(2))).thenReturn(priceWithId(2, new Price(300, "kiwi", new Timestamp(114, 1, 1, 0, 0, 0, 0))));

        final Response response = target("/products/1/prices/2")
                .request()
                .get();


        assertThat(response.getStatus(), is(200));

        final Map price = response.readEntity(Map.class);


        assertThat(price.get("price"), is(300));
        assertThat(price.get("modifiedBy"), is("kiwi"));
        assertThat(price.get("modifiedTimestamp"), is("2014-02-01 00:00:00.0"));
        assertThat((String) price.get("uri"), endsWith("products/1/prices/2"));
    }

    @Test
    public void should_create_new_price_of_a_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(productWithId(1, new Product("first", "good")));
        when(mockPriceMapper.createPrice(anyObject(), anyObject())).thenReturn(1);

        HashMap newPriceJson = new HashMap<String, String>();
        newPriceJson.put("price", 300);
        newPriceJson.put("modifiedBy", "kiwi");
        newPriceJson.put("modifiedTimestamp", new Timestamp(114, 1, 1, 0, 0, 0, 0));

        final Response response = target("/products/1/prices")
                .request()
                .post(Entity.entity(newPriceJson, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_404_when_price_not_exist() {
        when(mockProductRepository.findProductById(1)).thenReturn(productWithId(1, new Product("first", "good")));
        when(mockPriceMapper.getPrice(anyObject(), eq(2))).thenThrow(new ResourceNotFoundException());

        final Response response = target("/products/1/prices/2")
                .request()
                .get();


        assertThat(response.getStatus(), is(404));
    }
}
