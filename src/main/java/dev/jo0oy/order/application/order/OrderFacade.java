package dev.jo0oy.order.application.order;

import dev.jo0oy.order.domain.notification.NotificationService;
import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.OrderInfo;
import dev.jo0oy.order.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderFacade {

    private final OrderService orderService;
    private final NotificationService notificationService;

    // 주문 등록
    public String registerOrder(OrderCommand.RegisterOrder command) {
        log.info("register order");

        var orderToken = orderService.registerOrder(command);
        notificationService.sendEmail(null, null, null);

        return orderToken;
    }

    // 결제
    public void payOrder(OrderCommand.PaymentReq paymentReq) {
        orderService.payOrder(paymentReq);
        notificationService.sendKakao("phoneNum", "Pay_Success");
    }

    // 주문 조회
    public OrderInfo.MainInfo getOrderInfo(String orderToken) {
        return orderService.getOrderInfo(orderToken);
    }

    // 선물 주문 배송지 정보 업데이트
    public void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoReq command) {
        orderService.updateReceiverInfo(orderToken, command);
        notificationService.sendKakao("phoneNum", "UpdateReceiverInfo_Success");
    }
}
