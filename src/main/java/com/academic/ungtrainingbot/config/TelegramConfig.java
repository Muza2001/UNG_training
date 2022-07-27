package com.academic.ungtrainingbot.config;

import com.academic.ungtrainingbot.service.BotService;
import com.academic.ungtrainingbot.util.BotMenu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramConfig extends TelegramLongPollingBot {

    private final BotService botService;

    public TelegramConfig(BotService botService) {
        this.botService = botService;
    }

    @Override
    public void onUpdateReceived(Update update) {
            if (update.hasMessage()){
                Message message = update.getMessage();
                if (message.hasText()) {
                    String text = message.getText();
                    switch (text) {
                        case BotMenu.START:
                            SendMessage sendMessage = botService.start(message);
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            break;
                        case BotMenu.MENU:
                            break;
                    }
                }
            }
    }

    @Override
    public String getBotToken() {
        return "5580382523:AAEERCdBRZG30kLaTAHGNCa9IKc4DY_QJZU";
    }

    @Override
    public String getBotUsername() {
        return "UNG_training_bot";
    }

}
