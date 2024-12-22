package org.example.controller;

import org.example.bot.LaunchPoolBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

    private final LaunchPoolBot launchPoolBot;

    public MyScheduler(LaunchPoolBot launchPoolBot) {
        this.launchPoolBot = launchPoolBot;
    }

    @Scheduled(fixedRate = 21600000) // Каждые 6 часов
    public void notifyActivePools() {
        launchPoolBot.notifyUpcomingPools();
    }
}
