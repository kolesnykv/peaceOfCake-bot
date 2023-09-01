package com.pieceofcakebot;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class KeyboardFactory {

    public ReplyKeyboard getGeneralKeyboard() {
        KeyboardRow row = new KeyboardRow();
        List <KeyboardRow> keyboardRowList = new ArrayList<>();
        row.add("Записатись у комʼюніті");
        row.add("Пройти тест на рівень знань");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("вартість занять");
        row2.add("більше про нас");
        keyboardRowList.add(row);
        keyboardRowList.add(row2);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard getYesOrNo() {
        KeyboardRow row = new KeyboardRow();
        row.add("Yes");
        row.add("No");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public ReplyKeyboard getGoBack() {
        KeyboardRow row = new KeyboardRow();
        row.add("Go Back");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(row));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
