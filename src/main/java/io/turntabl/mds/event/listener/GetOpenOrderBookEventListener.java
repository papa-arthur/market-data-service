package io.turntabl.mds.event.listener;

import io.turntabl.mds.event.GetOpenOrderBookEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GetOpenOrderBookEventListener implements ApplicationListener<GetOpenOrderBookEvent> {


    @Override
    public void onApplicationEvent(GetOpenOrderBookEvent event) {
        var marketData = event.getData();
        var exchange = marketData.getExchange();

        if (exchange.equals("MAL1")){
            //TODO: get OpenOrderBook  from EXCHANGE1

            //TODO: save OpenOrderBook  to redis data

        }else {

            //TODO: get OpenOrderBook  from EXCHANGE1

            //TODO: save OpenOrderBook  to redis data

        }

    }
}
