package dev.jo0oy.order.domain.order;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder command);

    OrderInfo.MainInfo getOrderInfo(String orderToken);

    void payOrder(OrderCommand.PaymentReq command);

    void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoReq command);
}
