package org.Mark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleEchoBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Це точка входу для будь-яких повідомлень або оновлень, отриманих від користувача<br>
     * Документи для «Оновити об’єкт: <a href="https://core.telegram.org/bots/api#update">...</a>
     */
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            Long userId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            //друк повідомлень користувача
            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            //Запит на будівництво, який потрібно надіслати в Telegram API
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(update.getMessage().getChat().getId().toString())
                    .text("Hello, I've received your text: " + textFromUser)
                    .build();
            try {
                //відправка повідомлення через Telegram API
                this.sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Exception when sending message: ", e);
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }


    @Override
    public String getBotUsername() {
        // ім’я користувача, яке ви надаєте своєму боту через BotFather (without @)
        return "Markzcollagebot ";
    }

    @Override
    public String getBotToken() {
        // не надавати токен репозиторію,
        // завжди надавайте його ззовні (наприклад, як змінну середовища)
        return "5695946975:AAECwsi4LkURzyTKp_kmrbHIuGO0FKyZEv8";
    }
}