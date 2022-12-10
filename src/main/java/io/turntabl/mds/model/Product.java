package io.turntabl.mds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ProductData")
public class Product implements Serializable {
    @Id
    private int SELL_LIMIT;
    private double BID_PRICE;
    private String TICKER;
    private double ASK_PRICE;
    private int BUY_LIMIT;
    private double MAX_PRICE_SHIFT;
    private double LAST_TRADED_PRICE;

}
