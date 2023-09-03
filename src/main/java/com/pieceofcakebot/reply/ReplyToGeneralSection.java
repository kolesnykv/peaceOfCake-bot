package com.pieceofcakebot.reply;

import com.pieceofcakebot.KeyboardFactory;
import com.pieceofcakebot.UserState;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

import static com.pieceofcakebot.UserState.GENERAL_SELECTION;
import static com.pieceofcakebot.UserState.PRICING;

public class ReplyToGeneralSection extends AbstractReply {
    public ReplyToGeneralSection(SilentSender sender, Map<Long, UserState> chatStates) {
        super(sender, chatStates);
    }

    @Override
    public void reply(Long chatId, Message message) {
        switch (message.getText()) {
            case "Вартість занять" -> promptWithKeyboardForState(chatId, "Choose suitable category",
                    KeyboardFactory.getPricingKeyboard(), PRICING);
            case "Більше про нас" -> promptWithKeyboardForState(chatId, "Переходь на наш інстаграм + ссылка",
                    KeyboardFactory.getGeneralKeyboard(), GENERAL_SELECTION);
            default -> unexpectedMessage(chatId);
        }
    }
}
