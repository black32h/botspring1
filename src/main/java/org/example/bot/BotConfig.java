package org.example.bot;

import lombok.Getter;
import lombok.Setter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    // Геттер для botUsername
    public String getBotUsername() {
        return botUsername;
    }

    // Геттер для botToken
    public String getBotToken() {
        return botToken;
    }
}

