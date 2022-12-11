package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.domain.order.payment.PayProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final OrderItemSetFactory orderItemSetFactory;
    private final OrderInfoMapper orderInfoMapper;
    private final PayProcessor payProcessor;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder command) {
        var storedOrder = orderStore.store(command.toEntity());
        orderItemSetFactory.storeOrderItem(storedOrder, command);

        return storedOrder.getOrderToken();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo.MainInfo getOrderInfo(String orderToken) {
        var order = orderReader.getOrder(orderToken);
        return orderInfoMapper.toInfo(order);
    }

    @Override
    @Transactional
    public void payOrder(OrderCommand.PaymentReq command) {
        var order = orderReader.getOrder(command.getOrderToken());
//        order.completeOrder();
        payProcessor.pay(order, command);
    }

    @Override
    @Transactional
    public void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoReq command) {
        var order = orderReader.getOrder(orderToken);
        order.updateDeliveryInfo(command.toDelivery());
        order.deliveryPrepare();
    }
}
