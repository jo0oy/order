package dev.jo0oy.order.domain.order.payment.validator;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 2)
@Component
public class PayMethodValidator implements PaymentValidator {

    @Override
    public void validate(Order order, OrderCommand.PaymentReq request) {
        if (!order.getPayMethod().equals(request.getPayMethod())) {
            throw new InvalidParamException("주문 과정에서의 결제 수단이 불일치합니다.");
        }
    }
}
