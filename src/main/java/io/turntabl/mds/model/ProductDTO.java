package io.turntabl.mds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int SELL_LIMIT;
    private double BID_PRICE;
    private String TICKER;
    private double ASK_PRICE;
    private int BUY_LIMIT;
    private double MAX_PRICE_SHIFT;
    private double LAST_TRADED_PRICE;

}
