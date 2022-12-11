package dev.jo0oy.order.domain.order.gift;

import dev.jo0oy.order.domain.order.OrderCommand;

public interface GiftOrderService {
    void payOrder(OrderCommand.PaymentReq request);
}
