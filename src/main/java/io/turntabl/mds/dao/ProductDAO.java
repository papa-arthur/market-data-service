package io.turntabl.mds.dao;

import io.turntabl.mds.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {

    public static final String HASH_KEY = "ProductDTO";

    @Autowired
    private RedisTemplate template;


    @CachePut("#exchangeHash")
    public ProductDTO save(String exchangeHash, ProductDTO productDTO) {
        template.opsForHash().put(exchangeHash, productDTO.TICKER(), productDTO);
        return productDTO;
    }

    @CachePut("#hashKey")
    public Map<String, ProductDTO> saveAll(String exchangeHash, Map<String, ProductDTO> map) {
        template.opsForHash().putAll(exchangeHash,map );
        return map;
    }

    public List<ProductDTO> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public ProductDTO findProductById(int id) {
        System.out.println("called findProductById() from DB");
        return (ProductDTO) template.opsForHash().get(HASH_KEY, id);
    }


    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed !!";
    }
}