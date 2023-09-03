package com.pieceofcakebot.reply;

import com.pieceofcakebot.KeyboardFactory;
import com.pieceofcakebot.UserState;
import org.apache.commons.lang3.StringUtils;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Map;
import java.util.Objects;

import static com.pieceofcakebot.UserState.GENERAL_SELECTION;

public abstract class AbstractReply {
    protected final SilentSender sender;
    protected final Map<Long, UserState> chatStates;

    public AbstractReply(SilentSender sender, Map<Long, UserState> chatStates) {
        this.sender = sender;
        this.chatStates = chatStates;
    }

    protected void promptWithKeyboardForState(long chatId, String text, ReplyKeyboard replyKeyboard, UserState userState) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (StringUtils.isNotBlank(text)) {
            sendMessage.setText(text);
        }
        if (Objects.nonNull(replyKeyboard)) {
            sendMessage.setReplyMarkup(replyKeyboard);
        }
        sender.execute(sendMessage);
        chatStates.put(chatId, userState);
    }

    public void unexpectedMessage(long chatId) {
        promptWithKeyboardForState(chatId, "I did not expect that. Returned to general selection",
                KeyboardFactory.getGeneralKeyboard(), GENERAL_SELECTION);
    }

    abstract public void reply(Long chatId, Message message);
}
