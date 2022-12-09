package io.turntabl.mds.event.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.mds.config.RedisConfig;
import io.turntabl.mds.dao.ProductDAO;
import io.turntabl.mds.event.GetProductDataEvent;
import io.turntabl.mds.feignclient.Exchange1Client;
import io.turntabl.mds.feignclient.Exchange2Client;
import io.turntabl.mds.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProductDataEventListener implements ApplicationListener<GetProductDataEvent> {

    @Autowired
    private Exchange1Client feignclient1;
    @Autowired
    private Exchange2Client feignclient2;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ChannelTopic topic;

    @Autowired
    RedisTemplate template;

    @Override
    @EventListener()
    public void onApplicationEvent(GetProductDataEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();

        System.out.println("GetProductDataEvenListener called ");

        //1. get ProductDTO Data from EXCHANGE1
        //2. save product data to redis data
        //3. product data to PRODUCT_DATA topic

        if (exchange.equals("MAL1")) {

           List<ProductDTO> productDTODataList = feignclient1.getProductData();

            System.out.println(productDTODataList);


            var productDataMap = productDTODataList.stream()
                    .collect(Collectors.toMap(ProductDTO::TICKER, productDTO -> productDTO));

            System.out.println(productDataMap);
            template.opsForHash().putAll("EXHANGE1_PD", productDataMap);

            productDAO.saveAll("EXHANGE1_PD", productDataMap);


            template.convertAndSend(topic.getTopic(), productDataMap);

        } else {

            //TODO: get ProductDTO Data from EXCHANGE1
            List<ProductDTO> productDTODataList = feignclient2.getProductData2();
            System.out.println(productDTODataList);

            var productDataMap = productDTODataList.stream()
                    .collect(Collectors.toMap(ProductDTO::TICKER, productDTO -> productDTO));


            template.opsForHash().putAll("EXHANGE2_PD", productDataMap);

            //TODO publish to PRODUCT_DATA topic (pub/sud)

            template.convertAndSend("PRODUCT_DATA", productDataMap.toString());

        }

    }


}
