package io.turntabl.mds.controller;

import io.turntabl.mds.model.ProductData;
import io.turntabl.mds.service.StompDataRetrieval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    StompDataRetrieval stompDataRetrieval;


    @MessageMapping("/charts")
    public void brodcastProductData(@Payload String message) {
        this.simpMessagingTemplate.convertAndSend("/data/charts", stompDataRetrieval.processWebSocket(message));
    }
}
