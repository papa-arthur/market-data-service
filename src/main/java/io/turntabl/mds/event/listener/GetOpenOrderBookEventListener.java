package io.turntabl.mds.event.listener;

import io.turntabl.mds.config.ProjectConfig;
import io.turntabl.mds.config.RedisConfig;
import io.turntabl.mds.event.listener.dao.OrderDataDAO;
import io.turntabl.mds.event.GetOpenOrderBookEvent;
import io.turntabl.mds.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
public class GetOpenOrderBookEventListener implements ApplicationListener<GetOpenOrderBookEvent> {

    @Autowired
    WebClient webClient;

    @Autowired
    RedisTemplate template;

    @Autowired
    OrderDataDAO orderDataDAO;

    private List<OrderData> orderBook;

    @Override
    @EventListener()
    public void onApplicationEvent(GetOpenOrderBookEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();
        System.out.println("GetOpenOrderBookEventListener received Event!");

        if (exchange.equals("MAL1")) {
            orderBook = getOrderBook(ProjectConfig.EXCHANGE1_URL, marketData.getProduct());
            System.out.println(orderBook);
            orderDataDAO.save(RedisConfig.EX1_ORDERBOOK_HASH, marketData.getProduct(), orderBook);
        } else {
            orderBook = getOrderBook(ProjectConfig.EXCHANGE2_URL, marketData.getProduct());
            System.out.println(orderBook);
            orderDataDAO.save(RedisConfig.EX2_ORDERBOOK_HASH, marketData.getProduct(), orderBook);
        }
    }

    private List<OrderData> getOrderBook(String baseUrl, String product) {
        return Arrays.stream(webClient.get()
                        .uri(baseUrl + "/orderbook/" + product + "/open")
                        .retrieve()
                        .bodyToMono(OrderData[].class)
                        .block())
                .toList();
    }
}
