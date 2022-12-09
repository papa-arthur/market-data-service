package io.turntabl.mds.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class OrderDataDAO {


    @Autowired
    RedisTemplate template;

}
