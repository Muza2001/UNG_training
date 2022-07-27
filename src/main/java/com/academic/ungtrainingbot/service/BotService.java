package com.academic.ungtrainingbot.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotService {

    public SendMessage start(Message message) {
        String name = message.getFrom().getFirstName();
        SendMessage sendMessage = inlineKeyBoardMarkup(message);
        sendMessage.setText("Assalomu Alekum " + name + " sizga qanday yordam berishimiz mumkun");
        return sendMessage;
    }

    public KeyboardRow oneInlineRow(String text){
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(text);
        keyboardRow.add(keyboardButton);
        return keyboardRow;
    }

    public KeyboardRow twoInlineRow(String text1, String text2){
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton1 = new KeyboardButton();
        KeyboardButton keyboardButton2 = new KeyboardButton();

        keyboardButton1.setText(text1);
        keyboardButton2.setText(text2);
        keyboardRow.add(keyboardButton1);
        keyboardRow.add(keyboardButton2);
        return keyboardRow;
    }

    public SendMessage inlineKeyBoardMarkup(Message message){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        String[] text = {
                "\uD83D\uDCDA Bizning kurslar",
                "ℹ️ Biz haqimizda",
                "\uD83D\uDCDE Aloqa uchun",
                "\uD83D\uDCCD Manzil",
                "✔️ Ro'yxatdan o'tish",
                "\uD83D\uDC69\u200D\uD83D\uDCBB STAFF"};

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(oneInlineRow(text[0]));
        rows.add(twoInlineRow(text[1], text[2]));
        rows.add(twoInlineRow(text[3], text[4]));
        rows.add(oneInlineRow(text[5]));

        replyKeyboardMarkup.setKeyboard(rows);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
