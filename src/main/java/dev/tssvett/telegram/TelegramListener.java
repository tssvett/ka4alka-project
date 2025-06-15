package dev.tssvett.telegram;

import dev.tssvett.properties.TelegramBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Корневой класс для прослушивания сообщений из телеграмма
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramListener implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TelegramBotProperties telegramBotProperties;
    private final TelegramHandler telegramHandler;

    @Override
    public String getBotToken() {
        return telegramBotProperties.token();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        telegramHandler.handleUpdate(update);
    }
}
