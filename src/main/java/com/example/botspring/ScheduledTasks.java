package com.example.botspring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 5000)  // Задача будет выполняться каждые 5 секунд
	public void reportCurrentTime() {
		System.out.println("Current time: " + System.currentTimeMillis());
	}
}
