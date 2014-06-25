import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.PriceMapper;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.resource.exception.ProductNotFoundException;
import org.kiwi.resource.exception.ProductNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsResourceTest extends JerseyTest {
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
    public void should_get_all_products() {
        when(mockProductRepository.all()).thenReturn(Arrays.asList(new Product(1, "first", "good"), new Product(2, "second", "good")));

        final Response response = target("/products")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
        final List products = response.readEntity(List.class);
        assertThat(products.size(), is(2));

        final Map product = (Map)products.get(0);
        assertThat(product.get("name"), is("first"));
        assertThat((String)product.get("uri"), endsWith("products/1"));
    }

    @Test
    public void should_get_one_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(new Product(1, "first", "good"));

        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
        final Map product = response.readEntity(Map.class);

        assertThat(product.get("name"), is("first"));
        assertThat(product.get("description"), is("good"));
        assertThat((String)product.get("uri"), endsWith("products/1"));
    }


    @Test
    public void should_get_404_when_product_not_found() {
        when(mockProductRepository.findProductById(100)).thenThrow(new ProductNotFoundException());

        final Response response = target("/products/100")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_create_product() {
        final Product newProduct = new Product(1, "new product", "good");
        when(mockProductRepository.createProduct(anyObject())).thenReturn(newProduct);

        HashMap newProductJson = new HashMap<String, String>();
        newProductJson.put("name", "new juice");

        final Response response = target("/products")
                .request()
                .post(Entity.entity(newProductJson, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("location"), endsWith("products/1"));
    }
}
