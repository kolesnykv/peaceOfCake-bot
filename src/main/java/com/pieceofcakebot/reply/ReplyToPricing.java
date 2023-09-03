package com.pieceofcakebot.reply;

import com.pieceofcakebot.KeyboardFactory;
import com.pieceofcakebot.UserState;
import com.pieceofcakebot.pricing.Pricing;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

import static com.pieceofcakebot.UserState.ADULTS_GROUP_PRICING;
import static com.pieceofcakebot.UserState.CHILDREN_GROUP_PRICING;
import static com.pieceofcakebot.UserState.GENERAL_SELECTION;
import static com.pieceofcakebot.UserState.PRICING;
import static com.pieceofcakebot.UserState.TEEN_GROUP_PRICING;

public class ReplyToPricing extends AbstractReply {
    public ReplyToPricing(SilentSender sender, Map<Long, UserState> chatStates) {
        super(sender, chatStates);
    }

    @Override
    public void reply(Long chatId, Message message) {
        switch (message.getText()) {
            case "Групові уроки для дорослих" -> promptWithKeyboardForState(chatId, "Choose package",
                    KeyboardFactory.getPackageKeyboard(), ADULTS_GROUP_PRICING);
            case "Групові уроки для підлітків" -> promptWithKeyboardForState(chatId, "Choose package",
                    KeyboardFactory.getPackageKeyboard(), TEEN_GROUP_PRICING);
            case "Групові уроки для дітей" -> promptWithKeyboardForState(chatId, "Choose package",
                    KeyboardFactory.getPackageKeyboard(), CHILDREN_GROUP_PRICING);
            case "Індивідуальні уроки" -> promptWithKeyboardForState(chatId, Pricing.IndividualPricing,
                    KeyboardFactory.getPricingKeyboard(), PRICING);
            case "Go back" -> promptWithKeyboardForState(chatId, "Going back",
                    KeyboardFactory.getGeneralKeyboard(), GENERAL_SELECTION);
            default -> unexpectedMessage(chatId);
        }
    }
}
