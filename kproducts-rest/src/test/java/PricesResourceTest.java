import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.resource.exception.ProductNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesResourceTest extends JerseyTest {
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
    public void should_get_all_prices_of_a_product() {
        when(mockProductRepository.findProductById(1)).thenReturn(new Product(1, "first", "good"));

        final Response response = target("/products/1/prices")
                .request()
                .get();


        assertThat(response.getStatus(), is(200));
    }
}
