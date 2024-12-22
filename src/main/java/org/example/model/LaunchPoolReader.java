package org.example.model;

import org.example.enums.PoolStatus;

public class LaunchPoolInfo {
    private String exchange;
    private String launchPool;
    private String pools;
    private String period;
    private PoolStatus status;

    public LaunchPoolInfo(String exchange, String launchPool, String pools, String period, PoolStatus status) {
        this.exchange = exchange;
        this.launchPool = launchPool;
        this.pools = pools;
        this.period = period;
        this.status = status;
    }

    // Геттери, сеттери, toString
}
