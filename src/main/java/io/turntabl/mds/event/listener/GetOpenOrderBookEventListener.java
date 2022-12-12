package io.turntabl.mds.event.listener;

import io.turntabl.mds.config.ProjectConfig;
import io.turntabl.mds.config.RedisConfig;
import io.turntabl.mds.dao.ExecutedOrderIdDAO;
import io.turntabl.mds.dao.OrderDataDAO;
import io.turntabl.mds.event.GetOpenOrderBookEvent;
import io.turntabl.mds.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetOpenOrderBookEventListener implements ApplicationListener<GetOpenOrderBookEvent> {

    private WebClient webClient;

    private OrderDataDAO orderDataDAO;

    private ExecutedOrderIdDAO executedOrderIdDAO;
    private List<OrderData> orderBook;
    private List<String> orderIds;


    @Autowired
    public GetOpenOrderBookEventListener(WebClient webClient, OrderDataDAO orderDataDAO,
                                         ExecutedOrderIdDAO executedOrderIdDAO) {
        this.webClient = webClient;
        this.orderDataDAO = orderDataDAO;
        this.executedOrderIdDAO = executedOrderIdDAO;
        this.orderBook = new ArrayList<>();
        this.orderIds = new ArrayList<>();
    }


    @Override
    @EventListener()
    public void onApplicationEvent(GetOpenOrderBookEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();
        System.out.println("GetOpenOrderBookEventListener received Event!");

        if (exchange.equals("MAL1")) {
            orderBook = getOrderBook(ProjectConfig.EXCHANGE1_URL, marketData.getProduct());
            orderIds = executedOrderIdDAO.getAllOrderIds();

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

    public List<OrderData> filterOrderData(List<String> orderIds, List<OrderData> orderBook) {
        if (orderIds.isEmpty()) {
            return orderBook;
        }

        return orderBook.stream()
                .filter(orderData -> !(orderIds.contains(orderData.orderID())))
                .collect(Collectors.toList());
    }
}
