package dev.jo0oy.order.infrastructure.order;

import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderStore;
import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOption;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOptionGroup;
import dev.jo0oy.order.infrastructure.order.orderItem.OrderItemOptionGroupRepository;
import dev.jo0oy.order.infrastructure.order.orderItem.OrderItemOptionRepository;
import dev.jo0oy.order.infrastructure.order.orderItem.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemOptionGroupRepository orderItemOptionGroupRepository;
    private final OrderItemOptionRepository orderItemOptionRepository;

    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderItem store(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup) {
        return orderItemOptionGroupRepository.save(orderItemOptionGroup);
    }

    @Override
    public OrderItemOption store(OrderItemOption orderItemOption) {
        return orderItemOptionRepository.save(orderItemOption);
    }
}
