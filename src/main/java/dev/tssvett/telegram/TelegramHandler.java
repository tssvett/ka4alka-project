package dev.tssvett.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramHandler {
    private final TelegramClient telegramClient;

    public void handleUpdate(Update update) {
        log.debug("Начинаем обработку апдейта: {}", update);
        try {
            telegramClient.execute(sendEchoMessage(update));
        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при обработке апдейта", e);
        }
        log.debug("Обработка апдейта завершена успешно");
    }

    private SendMessage sendEchoMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String echoMessage = update.getMessage().getText();
        log.info("Отправка сообщения {} в чат {}", echoMessage, chatId);

        return SendMessage
                .builder()
                .chatId(chatId)
                .text(echoMessage)
                .build();
    }
}
