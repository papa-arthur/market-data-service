package io.turntabl.mds.service;

import io.turntabl.mds.model.MarketData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueingService {
    @Autowired
    RabbitTemplate template;

    public void sendTrackingMessage(String exchange, String routingKey, MarketData orderData){

        template.convertAndSend(exchange, routingKey, orderData);

    }

}
