package io.turntabl.mds.event;

import io.turntabl.mds.model.MarketData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class TrackOrderEvent extends ApplicationEvent {

    private MarketData data;
    public TrackOrderEvent(MarketData data) {
        super(data);
        this.data = data;

    }
}
