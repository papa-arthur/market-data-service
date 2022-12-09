package io.turntabl.mds.event.listener;

import io.turntabl.mds.event.GetOrderBookEvent;
import io.turntabl.mds.model.OrderData;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderBookEventListener implements ApplicationListener<GetOrderBookEvent> {

    @Override
    public void onApplicationEvent(GetOrderBookEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();
        List<OrderData> orderBook = List.of(new OrderData());


        if (exchange.equals("MAL1")) {

            //TODO get order book from EXCHANGE1


            var order = orderBook.stream()
                    .filter(orderData -> orderData.getOrderId().equals(marketData.getOrderID()))
                    .findFirst().get();

            //TODO send to TRACKING QUEUE


        } else {
            //TODO get order book from EXCHANGE2


            var order = orderBook.stream()
                    .filter(orderData -> orderData.getOrderId().equals(marketData.getOrderID()))
                    .findFirst().get();

            //TODO send to TRACKING QUEUE


        }


    }
}
