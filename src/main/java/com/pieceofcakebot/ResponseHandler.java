package com.pieceofcakebot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.Map;

import static com.pieceofcakebot.UserState.AWAITING_NAME;
import static com.pieceofcakebot.UserState.GENERAL_SELECTION;


public class ResponseHandler {
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        chatStates = db.getMap("chatStates");
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Welcome to the piece of cake! \nWhat`s your name?");
        sender.execute(message);
        chatStates.put(chatId, AWAITING_NAME);
    }

    public void replyToButtons(long chatId, Message message) {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        }

        switch (chatStates.get(chatId)) {
            case AWAITING_NAME -> replyToName(chatId, message);
            case GENERAL_SELECTION -> replyToGeneralSection(chatId, message);
            case GO_BACK -> replyToGoBack(chatId, message);
            default -> unexpectedMessage(chatId);
        }
    }

    private void unexpectedMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("I did not expect that.");
        sender.execute(sendMessage);
    }

    private void stopChat(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Thank you\nPress /start to order again");
        chatStates.remove(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(sendMessage);
    }

    private void promptWithKeyboardForState(long chatId, String text, ReplyKeyboard YesOrNo, UserState awaitingReorder) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(YesOrNo);
        sender.execute(sendMessage);
        chatStates.put(chatId, awaitingReorder);
    }

    private void replyToName(long chatId, Message message) {
        promptWithKeyboardForState(chatId, "Hello " + message.getText() + ". What would you like to have?",
                KeyboardFactory.getGeneralKeyboard(),
                GENERAL_SELECTION);
    }

    private void replyToGoBack(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("going back");
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(KeyboardFactory.getGeneralKeyboard());
        sender.execute(sendMessage);
        chatStates.put(chatId, GENERAL_SELECTION);
    }


    private void replyToGeneralSection(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if ("вартість занять".equalsIgnoreCase(message.getText())) {
            sendMessage.setText(Pricing.lightPackage + "\n" + Pricing.favouritePackage);
            sendMessage.setReplyMarkup(KeyboardFactory.getGoBack());
            sender.execute(sendMessage);
            chatStates.put(chatId, UserState.GO_BACK);
        }
        else {
            sendMessage.setText("We don't sell " + message.getText() + ". Please select from the options below.");
            sendMessage.setReplyMarkup(KeyboardFactory.getGoBack());
            sender.execute(sendMessage);
        }
    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }
}
