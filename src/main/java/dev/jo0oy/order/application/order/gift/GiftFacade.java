package dev.jo0oy.order.application.order.gift;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.gift.GiftOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftFacade {

    private final GiftOrderService giftOrderService;

    public void payOrder(OrderCommand.PaymentReq request) {
        giftOrderService.payOrder(request);
    }
}
