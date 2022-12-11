package dev.jo0oy.order.infrastructure.order.payment;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.payment.PayMethod;

public interface PaymentApiCaller<T> {
    boolean checkPayMethod(PayMethod payMethod);

    T pay(OrderCommand.PaymentReq paymentReq);
}
