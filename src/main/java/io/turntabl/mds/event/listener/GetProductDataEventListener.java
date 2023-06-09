package io.turntabl.mds.event.listener;

import io.turntabl.mds.config.RedisConfig;
import io.turntabl.mds.dao.ProductDAO;
import io.turntabl.mds.event.GetProductDataEvent;
import io.turntabl.mds.feignclient.Exchange1Client;
import io.turntabl.mds.feignclient.Exchange2Client;
import io.turntabl.mds.model.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetProductDataEventListener implements ApplicationListener<GetProductDataEvent> {

    @Autowired
    Exchange1Client exchange1Client;
    @Autowired
    Exchange2Client exchange2Client;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ChannelTopic topic;

    @Autowired
    RedisTemplate template;

    private List<ProductData> productDataDataList = new ArrayList<>();
    private Map<String, ProductData> productDataMap = new HashMap<>();

    @Override
    @EventListener()
    public void onApplicationEvent(GetProductDataEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();

        //1. get ProductData Data from EXCHANGE1
        //2. save product data to redis data
        //3. publish data to PRODUCT_DATA topic

        if (exchange.equals("MAL1")) {

            productDataDataList = exchange1Client.getProductData();

            productDataMap = productDataDataList.stream()
                    .collect(Collectors.toMap(ProductData::TICKER, productDTO -> productDTO));

            productDAO.saveAll(RedisConfig.EX1_PRODUCT_DATA_HASH, productDataMap);

        } else {
            productDataDataList = exchange2Client.getProductData();

            productDataMap = productDataDataList.stream()
                    .collect(Collectors.toMap(ProductData::TICKER, productDTO -> productDTO));

            productDAO.saveAll(RedisConfig.EX2_PRODUCT_DATA_HASH, productDataMap);
        }

        template.convertAndSend(topic.getTopic(), productDataMap);

    }

}