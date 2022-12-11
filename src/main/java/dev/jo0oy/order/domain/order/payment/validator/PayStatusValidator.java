package dev.jo0oy.order.domain.order.payment.validator;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 3)
@Component
public class PayStatusValidator implements PaymentValidator{
    @Override
    public void validate(Order order, OrderCommand.PaymentReq request) {
        if (order.isPaidOrder()) {
            throw new InvalidParamException("이미 결제 완료된 주문입니다.");
        }

        // 보상 트랜잭션을 위한 선처리 - 주문 완료 처리
        order.completeOrder();
    }
}
