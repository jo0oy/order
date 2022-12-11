package dev.jo0oy.order.infrastructure.order.orderItem;

import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
