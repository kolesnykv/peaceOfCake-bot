package com.pieceofcakebot;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class KeyboardFactory {

    public ReplyKeyboard getGeneralKeyboard() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Записатись у комʼюніті");
        firstRow.add("Пройти тест на рівень знань");
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Вартість занять");
        secondRow.add("Більше про нас");
        keyboardRowList.add(firstRow);
        keyboardRowList.add(secondRow);
        return getResizedReplyKeyboard(keyboardRowList);
    }

    public ReplyKeyboard getPricingKeyboard() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Групові уроки для дорослих");
        firstRow.add("Групові уроки для підлітків");
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Групові уроки для дітей");
        secondRow.add("Індивідуальні уроки");
        keyboardRowList.add(firstRow);
        keyboardRowList.add(secondRow);
        keyboardRowList.add(getGoBackRow());
        return getResizedReplyKeyboard(keyboardRowList);
    }

    public ReplyKeyboard getPackageKeyboard() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Light");
        firstRow.add("Favourite");
        firstRow.add("Delicious");
        keyboardRowList.add(firstRow);
        keyboardRowList.add(getGoBackRow());
        return getResizedReplyKeyboard(keyboardRowList);
    }

    public ReplyKeyboard getGoBack() {
        return getResizedReplyKeyboard(List.of(getGoBackRow()));
    }

    @NotNull
    private static KeyboardRow getGoBackRow() {
        KeyboardRow row = new KeyboardRow();
        row.add("Go back");
        return row;
    }

    @NotNull
    private static ReplyKeyboardMarkup getResizedReplyKeyboard(List<KeyboardRow> keyboardRowList) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
