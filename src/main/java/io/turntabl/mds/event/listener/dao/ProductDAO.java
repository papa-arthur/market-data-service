package io.turntabl.mds.event.listener.dao;

import io.turntabl.mds.model.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {

    public static final String HASH_KEY = "ProductData";

    @Autowired
    private RedisTemplate template;


    @CachePut("#PRODUCT_DATA_HASH")
    public ProductData save(String exchangeHash, ProductData productData) {
        template.opsForHash().put(exchangeHash, productData.TICKER(), productData);
        return productData;
    }

    @CachePut("#PRODUCT_DATA_HASH")
    public Map<String, ProductData> saveAll(String exchangeHash, Map<String, ProductData> map) {
        template.opsForHash().putAll(exchangeHash,map );
        return map;
    }

    public List<ProductData> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public ProductData findProductById(int id) {
        System.out.println("called findProductById() from DB");
        return (ProductData) template.opsForHash().get(HASH_KEY, id);
    }


    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed !!";
    }
}