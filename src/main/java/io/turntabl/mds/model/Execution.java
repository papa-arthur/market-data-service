package io.turntabl.mds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Execution {

    private String timeStamp;
    private double price;
    private int quantity;
}
