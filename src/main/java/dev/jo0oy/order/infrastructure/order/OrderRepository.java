package dev.jo0oy.order.infrastructure.order;

import dev.jo0oy.order.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderToken(String orderToken);
}
