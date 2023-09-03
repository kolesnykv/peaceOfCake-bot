package com.pieceofcakebot.reply;

import com.pieceofcakebot.KeyboardFactory;
import com.pieceofcakebot.UserState;
import com.pieceofcakebot.pricing.Pricing;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

import static com.pieceofcakebot.UserState.ADULTS_GROUP_PRICING;
import static com.pieceofcakebot.UserState.PRICING;

public class ReplyToAdultsGroupPricing extends AbstractReply {

    public ReplyToAdultsGroupPricing(SilentSender sender, Map<Long, UserState> chatStates) {
        super(sender, chatStates);
    }

    @Override
    public void reply(Long chatId, Message message) {
        switch (message.getText()) {
            case "Light" -> promptWithKeyboardForState(chatId, Pricing.AdultsLightPackage,
                    KeyboardFactory.getPackageKeyboard(), ADULTS_GROUP_PRICING);
            case "Favourite" -> promptWithKeyboardForState(chatId, Pricing.AdultsFavouritePackage,
                    KeyboardFactory.getPackageKeyboard(), ADULTS_GROUP_PRICING);
            case "Delicious" -> promptWithKeyboardForState(chatId, Pricing.AdultsDeliciousPackage,
                    KeyboardFactory.getPackageKeyboard(), ADULTS_GROUP_PRICING);
            case "Go back" -> promptWithKeyboardForState(chatId, "Going back",
                    KeyboardFactory.getPricingKeyboard(), PRICING);
            default -> unexpectedMessage(chatId);
        }
    }
}
