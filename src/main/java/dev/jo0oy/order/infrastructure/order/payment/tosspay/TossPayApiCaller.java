package dev.jo0oy.order.infrastructure.order.payment.tosspay;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import dev.jo0oy.order.infrastructure.order.payment.PaymentApiCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TossPayApiCaller implements PaymentApiCaller {

    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return payMethod == PayMethod.TOSS_PAY;
    }

    @Override
    public String pay(OrderCommand.PaymentReq paymentReq) {
        return "";
    }
}
