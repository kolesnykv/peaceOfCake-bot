package com.pieceofcakebot.reply;

import com.pieceofcakebot.UserState;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

public class Reply extends AbstractReply {
    private final ReplyToName replyToName;
    private final ReplyToGeneralSection replyToGeneralSection;
    private final ReplyToPricing replyToPricing;
    private final ReplyToAdultsGroupPricing replyToAdultsGroupPricing;
    private final ReplyToTeensGroupPricing replyToTeensGroupPricing;
    private final ReplyToChildrenGroupPricing replyToChildrenGroupPricing;

    public Reply(SilentSender sender, Map<Long, UserState> chatStates) {
        super(sender, chatStates);
        replyToName = new ReplyToName(sender, chatStates);
        replyToGeneralSection = new ReplyToGeneralSection(sender, chatStates);
        replyToPricing = new ReplyToPricing(sender, chatStates);
        replyToAdultsGroupPricing = new ReplyToAdultsGroupPricing(sender, chatStates);
        replyToTeensGroupPricing = new ReplyToTeensGroupPricing(sender, chatStates);
        replyToChildrenGroupPricing = new ReplyToChildrenGroupPricing(sender, chatStates);
    }


    @Override
    public void reply(Long chatId, Message message) {
        switch (chatStates.get(chatId)) {
            case AWAITING_NAME -> replyToName.reply(chatId, message);
            case GENERAL_SELECTION -> replyToGeneralSection.reply(chatId, message);
            case PRICING -> replyToPricing.reply(chatId, message);
            case ADULTS_GROUP_PRICING -> replyToAdultsGroupPricing.reply(chatId, message);
            case TEEN_GROUP_PRICING -> replyToTeensGroupPricing.reply(chatId, message);
            case CHILDREN_GROUP_PRICING -> replyToChildrenGroupPricing.reply(chatId, message);
            default -> unexpectedMessage(chatId);
        }
    }
}
