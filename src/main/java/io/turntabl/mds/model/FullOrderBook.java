package io.turntabl.mds.model;

import java.io.Serializable;
import java.util.List;

public record FullOrderBook(List<OrderData> fullOrderBook) implements Serializable {}
