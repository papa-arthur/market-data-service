package io.turntabl.mds.event.listener;

import io.turntabl.mds.config.MQConfig;
import io.turntabl.mds.config.ProjectConfig;
import io.turntabl.mds.event.TrackOrderEvent;
import io.turntabl.mds.model.OrderData;
import io.turntabl.mds.service.QueueingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TrackOrderEventListener implements ApplicationListener<TrackOrderEvent> {

    @Autowired
    WebClient webClient;
    @Autowired
    QueueingService queueingService;
    private List<OrderData> orderBook = new ArrayList<>();
    private  OrderData orderData;

    private Map<String, OrderData> orderBookMap = new HashMap<>();
    @Override
    @EventListener()
    public void onApplicationEvent(TrackOrderEvent event) {
        var marketData = event.getData();


        queueingService.sendTrackingMessage(MQConfig.ORDER_EXCHANGE, MQConfig.TRACKING_ROUTING_KEY, marketData);

    }



    private List<OrderData> getOrders(String baseUrl, String product) {
        return Arrays.stream(webClient.get()
                        .uri(baseUrl + "/orderbook/" + product)
                        .retrieve()
                        .bodyToMono(OrderData[].class)
                        .block())
                .toList();
    }

}

/**
 *         if(exchange.equals("MAL1")){
 *             orderBook = getOrders(ProjectConfig.EXCHANGE1_URL, marketData.getProduct());
 *
 *             var orderBookMap = orderBook.stream().collect(Collectors.toMap(
 *                     OrderData::orderID,orderData -> orderData
 *             ));
 *
 *
 *
 *         }else {
 *             orderBook = getOrders(ProjectConfig.EXCHANGE2_URL, marketData.getProduct());
 *             var orderBookMap = orderBook.stream().collect(Collectors.toMap(
 *                     OrderData::orderID,orderData -> orderData
 *             ));
 *         }
 */