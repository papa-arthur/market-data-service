package io.turntabl.mds.service;

import io.turntabl.mds.feignclient.Exchange1Client;
import io.turntabl.mds.feignclient.Exchange2Client;
import io.turntabl.mds.model.ProductData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StompDataRetrieval {

    @Autowired
    Exchange1Client exchange1Client;
    @Autowired
    Exchange2Client exchange2Client;
    private List<ProductData> productDataDataList = new ArrayList<>();


    public List<ProductData> processWebSocket(String message) {


        if (message.equals("MAL1")) {

            productDataDataList = exchange1Client.getProductData();
        } else {
            productDataDataList = exchange2Client.getProductData();
        }


        return productDataDataList;
    }}