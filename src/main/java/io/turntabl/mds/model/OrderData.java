package io.turntabl.mds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
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
}
