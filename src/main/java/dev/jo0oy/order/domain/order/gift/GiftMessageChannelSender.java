package dev.jo0oy.order.domain.order.gift;

public interface GiftMessageChannelSender {
    void payComplete(GiftPayCompleteMessage message);
}
