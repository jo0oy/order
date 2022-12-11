package dev.jo0oy.order.domain.order.payment.validator;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator{

    @Override
    public void validate(Order order, OrderCommand.PaymentReq request) {
        if (!order.calculateTotalAmount().equals(request.getAmount())) {
            throw new InvalidParamException("주문 가격이 불일치합니다.");
        }
    }
}
