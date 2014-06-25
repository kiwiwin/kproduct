import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.resource.exception.ProductNotFoundException;
import org.kiwi.resource.exception.ProductNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsResourceTest extends JerseyTest {
    @Mock
    private ProductRepository mockProductRepository;

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi.resource")
                .register(ProductNotFoundExceptionHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(mockProductRepository).to(ProductRepository.class);
                    }
                });
    }

    @Test
    public void should_get_all_products() {
        when(mockProductRepository.all()).thenReturn(Arrays.asList(new Product("first"), new Product("second")));

        final Response response = target("/products")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
        final List products = response.readEntity(List.class);
        assertThat(products.size(), is(2));
    }

    @Test
    public void should_get_one_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(new Product("first"));

        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_get_404_when_product_not_found() {
        when(mockProductRepository.findProductById(100)).thenThrow(new ProductNotFoundException());

        final Response response = target("/products/100")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }
}
