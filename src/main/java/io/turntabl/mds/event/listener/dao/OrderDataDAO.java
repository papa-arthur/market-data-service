package io.turntabl.mds.event.listener.dao;

import io.turntabl.mds.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDataDAO {


    @Autowired
    RedisTemplate template;



    @CachePut("#ORDERBOOK_HASH")
    public void save(String exchangeHash, String product,  List<OrderData> orderBook) {
        template.opsForHash().put(exchangeHash,  product, orderBook);
    }

    @CachePut("#ORDERBOOK_HASH")
    public void saveAll(String exchangeHash, Map<String, List<OrderData>> map) {
        template.opsForHash().putAll(exchangeHash,map );
    }

}
