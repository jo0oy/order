package dev.jo0oy.order.infrastructure.order.payment;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.payment.PayProcessor;
import dev.jo0oy.order.domain.order.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PayProcessorImpl implements PayProcessor {

    private final List<PaymentValidator> paymentValidatorList;
    private final List<PaymentApiCaller<?>> paymentApiCallerList;

    @Override
    public void pay(Order order, OrderCommand.PaymentReq command) {
        paymentValidatorList.forEach(paymentValidator
                -> paymentValidator.validate(order, command));

        var paymentApiCaller = getPaymentApiCaller(command);
        paymentApiCaller.pay(command);
    }

    private PaymentApiCaller<?> getPaymentApiCaller(OrderCommand.PaymentReq command) {
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller
                        -> paymentApiCaller.checkPayMethod(command.getPayMethod()))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }
}
