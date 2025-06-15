package dev.tssvett.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("telegram.bot")
public record TelegramBotProperties(
        String token
) {
}
