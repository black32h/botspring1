package org.example.controller;

import org.example.bot.LaunchPoolBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final LaunchPoolBot launchPoolBot;

    public ScheduledTasks(LaunchPoolBot launchPoolBot) {
        this.launchPoolBot = launchPoolBot;
    }

    @Scheduled(fixedRate = 21600000) // Кожні 6 годин
    public void notifyActivePools() {
        launchPoolBot.notifyUpcomingPools();
    }
}
