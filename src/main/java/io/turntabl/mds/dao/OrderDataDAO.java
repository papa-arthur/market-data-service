package io.turntabl.mds.dao;

import io.turntabl.mds.model.FullOrderBook;
import io.turntabl.mds.model.OrderData;
import io.turntabl.mds.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDataDAO {


    @Autowired
    RedisTemplate template;



    @CachePut("#exchangeHash")
    public void save(String exchangeHash, String product,  List<OrderData> orderBook) {
        template.opsForHash().put(exchangeHash,  product, orderBook);
    }

    @CachePut("#exchangeHash")
    public void saveAll(String exchangeHash, Map<String, List<OrderData>> map) {
        template.opsForHash().putAll(exchangeHash,map );
    }

}
