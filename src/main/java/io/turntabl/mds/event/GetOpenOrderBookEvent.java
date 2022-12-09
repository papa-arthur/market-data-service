package io.turntabl.mds.event;

import io.turntabl.mds.model.MarketData;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class GetOpenOrderBookEvent extends ApplicationEvent {
    private MarketData data;
    public GetOpenOrderBookEvent(MarketData data) {
        super(data);
        this.data = data;
        System.out.println("GetOpenOrderBookEvent event created!");
    }


}
