package io.turntabl.mds.dao;

import io.turntabl.mds.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExecutedOrderIdDAO {

    @Autowired
    RedisTemplate template;

    @Cacheable("#EXECUTED_ORDER_ID_LIST")
    public boolean hasKey(String orderID) {
        return template.opsForHash().hasKey(RedisConfig.EXECUTED_ORDER_ID_LIST, orderID);
    }


    @Cacheable("#EXECUTED_ORDER_ID_LIST")
    public List<String> getAllOrderIds() {
        return template.opsForHash().values(RedisConfig.EXECUTED_ORDER_ID_LIST);
    }
}
