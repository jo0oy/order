package dev.jo0oy.order.domain.order.payment.validator;

import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;

public interface PaymentValidator {
    void validate(Order order, OrderCommand.PaymentReq request);
}
