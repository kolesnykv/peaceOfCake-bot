package com.pieceofcakebot.reply;

import com.pieceofcakebot.KeyboardFactory;
import com.pieceofcakebot.UserState;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

import static com.pieceofcakebot.UserState.GENERAL_SELECTION;

public class ReplyToName extends AbstractReply {
    public ReplyToName(SilentSender sender, Map<Long, UserState> chatStates) {
        super(sender, chatStates);
    }

    @Override
    public void reply(Long chatId, Message message) {
        promptWithKeyboardForState(chatId, "Hello " + message.getText() + ". What would you like to have?",
                KeyboardFactory.getGeneralKeyboard(), GENERAL_SELECTION);
    }
}
