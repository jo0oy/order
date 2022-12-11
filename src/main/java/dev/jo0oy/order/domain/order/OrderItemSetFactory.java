package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.domain.order.orderItem.OrderItem;

import java.util.List;

public interface OrderItemSetFactory {

    List<OrderItem> storeOrderItem(Order order, OrderCommand.RegisterOrder command);
}
