package dev.jo0oy.order.interfaces.order;

import dev.jo0oy.order.application.order.OrderFacade;
import dev.jo0oy.order.common.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderFacade orderFacade;
    private final OrderDtoMapper orderDtoMapper;

    @PostMapping("/api/v1/order")
    public ResponseEntity<?> registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request) {
        var data = orderFacade.registerOrder(orderDtoMapper.toCommand(request));

        return ResponseEntity.created(URI.create("/api/v1/order"))
                .body(orderDtoMapper.toResponseDto(data));
    }

    @GetMapping("/api/v1/order/{orderToken}")
    public ResponseEntity<?> getOrderInfo(@PathVariable("orderToken") String orderToken) {
        var orderInfo = orderFacade.getOrderInfo(orderToken);
        return ResponseEntity.ok(ResultResponse.res(true, orderDtoMapper.toResponseDto(orderInfo)));
    }

    @PostMapping("/api/v1/order/payment-order")
    public ResponseEntity<?> paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest paymentRequest) {
        var paymentCommand = orderDtoMapper.toCommand(paymentRequest);
        orderFacade.payOrder(paymentCommand);
        return ResponseEntity.ok()
                .body(ResultResponse.res(true, "결제가 정상적으로 완료처리 되었습니다."));
    }
}
