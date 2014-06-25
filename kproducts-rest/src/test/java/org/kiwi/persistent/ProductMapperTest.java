package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductMapperTest {

    private ProductMapper productMapper;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        productMapper = sqlSession.getMapper(ProductMapper.class);
        productMapper.createProduct(new Product("apple juice", "good"));
        productMapper.createProduct(new Product("orange juice", "bad"));
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
}
