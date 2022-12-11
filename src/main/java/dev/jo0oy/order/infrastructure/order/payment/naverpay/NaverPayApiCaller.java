package dev.jo0oy.order.infrastructure.order.payment.naverpay;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import dev.jo0oy.order.infrastructure.order.payment.PaymentApiCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaverPayApiCaller implements PaymentApiCaller {
    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return PayMethod.NAVER_PAY == payMethod;
    }

    @Override
    public String pay(OrderCommand.PaymentReq paymentReq) {
        // TODO - 구현



        return "";
    }
}

