package io.turntabl.mds.controller;

import io.turntabl.mds.event.GetOrderBookEvent;
import io.turntabl.mds.event.GetOpenOrderBookEvent;
import io.turntabl.mds.event.GetProductDataEvent;
import io.turntabl.mds.model.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    RedisTemplate template;
     @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("/md")
    public  boolean receiveData(@RequestBody MarketData data){

        boolean result = template.opsForHash().hasKey("PLACED_ORDERS", "EXEC_ORDER_ID");

        if (result){

            publisher.publishEvent(new GetOrderBookEvent(data));

        }

        publisher.publishEvent(new GetProductDataEvent(data));
        publisher.publishEvent(new GetOpenOrderBookEvent(data));

        return true;
    }
}
