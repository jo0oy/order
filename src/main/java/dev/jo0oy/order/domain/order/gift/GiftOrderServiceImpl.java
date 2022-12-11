package dev.jo0oy.order.domain.order.gift;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.OrderReader;
import dev.jo0oy.order.domain.order.payment.PayProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftOrderServiceImpl implements GiftOrderService {

    private final OrderReader orderReader;
    private final PayProcessor payProcessor;
    private final GiftMessageChannelSender giftMessageChannelSender;

    @Override
    @Transactional
    public void payOrder(OrderCommand.PaymentReq request) {
        log.info("GiftOrderService.paymentOrder request = {}", request);

        var order = orderReader.getOrder(request.getOrderToken());

        // 보상 트랜잭션 -> PayStatusValidator 에서 order.completeOrder() 실행
        payProcessor.pay(order, request);

        giftMessageChannelSender.payComplete(new GiftPayCompleteMessage(order.getOrderToken()));
    }
}
