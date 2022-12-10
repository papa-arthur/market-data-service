package io.turntabl.mds.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


public record Execution(
        String timestamp,
        double price,
        int quantity) implements Serializable {}



/**
 *     private String timeStamp;
 *     private double price;
 *     private int quantity;
 */