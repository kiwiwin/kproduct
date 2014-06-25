import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductsResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi.resource");
    }

    @Test
    public void should_get_all_products() {
        final Response response = target("/products")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }
}
