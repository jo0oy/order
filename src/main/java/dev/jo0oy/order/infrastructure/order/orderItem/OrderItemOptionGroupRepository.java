package dev.jo0oy.order.infrastructure.order.orderItem;

import dev.jo0oy.order.domain.order.orderItem.OrderItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, Long> {
}
