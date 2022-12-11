package io.turntabl.mds.controller;

import io.turntabl.mds.dao.ExecutedOrderIdDAO;
import io.turntabl.mds.event.GetOpenOrderBookEvent;
import io.turntabl.mds.event.GetProductDataEvent;
import io.turntabl.mds.event.TrackOrderEvent;
import io.turntabl.mds.model.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    ExecutedOrderIdDAO executedOrderIdDAO;
     @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping("/md")
    public  boolean receiveData(@RequestBody MarketData data){

        boolean result =  executedOrderIdDAO.hasKey(  data.getOrderID());

        if (result){

            publisher.publishEvent(new TrackOrderEvent(data));
            System.out.println(data.getOrderID());

        }

        publisher.publishEvent(new GetProductDataEvent(data));
        publisher.publishEvent(new GetOpenOrderBookEvent(data));

        return true;
    }
}
