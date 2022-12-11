package dev.jo0oy.order.domain.order;

public interface OrderReader {
    Order getOrder(String orderToken);

    Order getOrder(Long orderId);
}
