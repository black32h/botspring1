package org.example.bot;

import org.example.bot.BotConfig;
import org.example.model.LaunchPoolInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LaunchPoolBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(LaunchPoolBot.class);

    private final BotConfig botConfig;

    private List<LaunchPoolInfo> pools;
    private Set<String> notifiedPools = new HashSet<>();

    @Value("${bot.chatId}")
    private String chatId;  // Динамічний chatId з конфігурації

    public LaunchPoolBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Обробка команд від користувача
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equalsIgnoreCase("/pools")) {
                sendPoolsList(update.getMessage().getChatId().toString());
            }
        }
    }

    private void sendPoolsList(String chatId) {
        StringBuilder message = new StringBuilder("Список пулів:\n");
        for (LaunchPoolInfo pool : pools) {
            message.append(pool.toString()).append("\n");
        }

        sendMessageToChat(chatId, message.toString());
    }

    public void setPools(List<LaunchPoolInfo> pools) {
        this.pools = pools;
    }

    public void notifyUpcomingPools() {
        for (LaunchPoolInfo pool : pools) {
            if (!notifiedPools.contains(pool.getLaunchPool())) {
                sendMessageToChat(chatId, pool.toString());
                notifiedPools.add(pool.getLaunchPool());
            }
        }
    }

    private void sendMessageToChat(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error sending message to chat: {}", e.getMessage(), e);
        }
    }
}
