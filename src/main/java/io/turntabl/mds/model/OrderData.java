package io.turntabl.mds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


public record OrderData(
        String product,
        int quantity,
        double price,
        String side,
        List<Execution> executions,
        String orderID,
        String orderType,
        int cumulatitiveQuantity,
        double cumulatitivePrice

) implements Serializable {}

/**
 *
 private String orderType;
 private String product;
 private String side;
 private  String orderId;
 private double price;
 private int qty;
 private int cumQty;
 private double cumPrx;
 private String exchange;
 private String timestamp;
 private List<Execution> executions;
 */
