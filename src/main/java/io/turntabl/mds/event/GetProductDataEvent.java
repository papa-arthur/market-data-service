package io.turntabl.mds.event;

import io.turntabl.mds.model.MarketData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class GetProductDataEvent extends ApplicationEvent {
    private MarketData data;

    public GetProductDataEvent(MarketData data) {
        super(data);
        this.data = data;

        System.out.println("GetProductDataEvent created");
        System.out.println(data);
    }
}
