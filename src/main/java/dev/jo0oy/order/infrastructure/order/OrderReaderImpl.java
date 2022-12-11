package dev.jo0oy.order.infrastructure.order;

import dev.jo0oy.order.common.exception.EntityNotFoundException;
import dev.jo0oy.order.domain.order.Order;
import dev.jo0oy.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
