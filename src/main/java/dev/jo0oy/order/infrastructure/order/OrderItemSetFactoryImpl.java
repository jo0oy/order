package dev.jo0oy.order.infrastructure.order;

import dev.jo0oy.order.domain.item.ItemReader;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.OrderItemSetFactory;
import dev.jo0oy.order.domain.order.OrderStore;
import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderItemSetFactoryImpl implements OrderItemSetFactory {
    private final ItemReader itemReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderItem> storeOrderItem(Order order, OrderCommand.RegisterOrder command) {
        var orderItems = command.getOrderItemList();

        return orderItems.stream()
                .map(orderItem -> {
                    var item = itemReader.getItem(orderItem.getItemToken());
                    var storedOrderItem = orderStore.store(orderItem.toEntity(order, item));

                    orderItem.getOrderItemOptionGroupList().forEach(registerOrderItemOptionGroup -> {
                        var storedOrderItemOptionGroup
                                = orderStore.store(registerOrderItemOptionGroup.toEntity(storedOrderItem));

                        registerOrderItemOptionGroup.getOrderItemOptionList()
                                .forEach(registerOrderItemOption -> {
                                    orderStore.store(registerOrderItemOption.toEntity(storedOrderItemOptionGroup));
                                });
                    });

                    return storedOrderItem;
                }).collect(Collectors.toList());
    }
}
