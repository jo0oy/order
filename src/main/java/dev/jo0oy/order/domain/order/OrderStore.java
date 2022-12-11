package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOption;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);

    OrderItem store(OrderItem orderItem);

    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);

    OrderItemOption store(OrderItemOption orderItemOption);
}
