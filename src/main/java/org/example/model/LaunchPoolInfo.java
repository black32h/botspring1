package org.example.model;

public class LaunchPoolInfo {
    private String exchange;
    private String launchPool;
    private String pools;
    private String period;
    private String status;
    public LaunchPoolInfo(String exchange, String launchPool, String pools, String period, LaunchPoolReader.PoolStatus status) {
        this.exchange = exchange;
        this.launchPool = launchPool;
        this.pools = pools;
        this.period = period;
        this.status = String.valueOf(status);

    }


    public String getLaunchPool() {
        return launchPool;
    }
}

