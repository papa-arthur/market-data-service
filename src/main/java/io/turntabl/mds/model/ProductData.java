package io.turntabl.mds.model;

import java.io.Serializable;

public record ProductData(
        int SELL_LIMIT,
        double BID_PRICE,
        String TICKER,
        double ASK_PRICE,
        int BUY_LIMIT,
        double MAX_PRICE_SHIFT,
        double LAST_TRADED_PRICE


) implements Serializable {}
//    private int SELL_LIMIT;
//    private double BID_PRICE;
//    private String TICKER;
//    private double ASK_PRICE;
//    private int BUY_LIMIT;
//    private double MAX_PRICE_SHIFT;
//    private double LAST_TRADED_PRICE;