package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductMapperTest {

    private ProductMapper productMapper;
    private PriceMapper priceMapper;
    private SqlSession sqlSession;
    private Product appleJuice;
    private Price appleJuicePrice;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        productMapper = sqlSession.getMapper(ProductMapper.class);
        priceMapper = sqlSession.getMapper(PriceMapper.class);
        setUpAppleJuice();
        productMapper.createProduct(new Product("orange juice", "bad"));
    }

    private void setUpAppleJuice() {
        appleJuice = new Product("apple juice", "good");
        productMapper.createProduct(appleJuice);
        appleJuicePrice = new Price(120, "kiwi", new Timestamp(114, 1, 1, 0, 0, 0, 0));
        priceMapper.createPrice(appleJuice, appleJuicePrice);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }

    @Test
    public void should_get_products() {
        assertThat(productMapper.all().size(), is(2));
    }

    @Test
    public void should_get_product() {
        final Product product = productMapper.findProductById(appleJuice.getId());
        assertThat(product.getName(), is("apple juice"));
        assertThat(product.getDescription(), is("good"));
        final Price currentPrice = product.getCurrentPrice();
        assertThat(currentPrice.getPrice(), is(120));
    }
}
