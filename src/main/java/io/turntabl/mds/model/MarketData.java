package io.turntabl.mds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketData {
    private String orderType;
    private String product;
    private String side;
    private  String orderID;
    private double price;
    private int qty;
    private int cumQty;
    private double cumPrx;
    private String exchange;
    private String timestamp;
}
