package dev.jo0oy.order.domain.order.payment;

import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;

public interface PayProcessor {
    void pay(Order order, OrderCommand.PaymentReq command);
}
