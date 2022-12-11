package dev.jo0oy.order.interfaces.order.gift;

import dev.jo0oy.order.application.order.OrderFacade;
import dev.jo0oy.order.application.order.gift.GiftFacade;
import dev.jo0oy.order.common.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GiftOrderApiController {

    private final OrderFacade orderFacade;
    private final GiftFacade giftFacade;
    private final GiftOrderDtoMapper giftOrderDtoMapper;

    @PostMapping("/api/v1/gift-orders/init")
    public ResponseEntity<?> registerGiftOrder(@RequestBody @Valid GiftOrderDto.RegisterOrderReq request) {
        var orderToken = orderFacade.registerOrder(giftOrderDtoMapper.toCommand(request));

        return ResponseEntity.created(URI.create("/api/v1/gift-orders/init"))
                .body(ResultResponse.res(true, giftOrderDtoMapper.toDto(orderToken)));
    }

    @PostMapping("/api/v1/gift-orders/pay-order")
    public ResponseEntity<?> payOrder(@RequestBody @Valid GiftOrderDto.PaymentReq request) {
        giftFacade.payOrder(giftOrderDtoMapper.toCommand(request));

        return ResponseEntity.ok(ResultResponse.res(true));
    }

    @PostMapping("/api/v1/gift-orders/{orderToken}/update-receiver-info")
    public ResponseEntity<?> updateReceiverInfo(@PathVariable("orderToken") String orderToken,
                                                @RequestBody @Valid GiftOrderDto.UpdateReceiverInfoReq request) {
        orderFacade.updateReceiverInfo(orderToken, giftOrderDtoMapper.toCommand(request));

        return ResponseEntity.ok(ResultResponse.res(true));
    }
}
