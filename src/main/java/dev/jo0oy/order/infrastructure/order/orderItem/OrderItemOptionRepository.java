package dev.jo0oy.order.infrastructure.order.orderItem;

import dev.jo0oy.order.domain.order.orderItem.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
