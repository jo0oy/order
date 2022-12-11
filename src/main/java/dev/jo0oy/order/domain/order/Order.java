package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.common.exception.IllegalStatusException;
import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.common.util.TokenGenerator;
import dev.jo0oy.order.domain.BaseTimeEntity;
import dev.jo0oy.order.domain.order.delivery.Delivery;
import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {

    private static final String ORDER_PREFIX = "ord_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderToken;

    private Long userId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private ZonedDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Delivery deliveryInfo;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @Builder
    public Order(Long userId, Delivery deliveryInfo, PayMethod payMethod) {

        if(Objects.isNull(userId)) throw new InvalidParamException("Order.userId");
        if(Objects.isNull(deliveryInfo)) throw new InvalidParamException("Order.deliveryInfo");
        if(Objects.isNull(payMethod)) throw new InvalidParamException("Order.payMethod");

        this.orderToken = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.userId = userId;
        this.orderedAt = ZonedDateTime.now();
        this.status = Status.INIT;
        this.deliveryInfo = deliveryInfo;
        this.payMethod = payMethod;
    }

    public Long calculateTotalAmount() {
        return this.orderItems.stream()
                .mapToLong(OrderItem::calculateTotalAmount)
                .sum();
    }

    public void completeOrder() {
        if (this.status != Status.INIT) {
            throw new IllegalStatusException("주문 완료 처리를 할 수 없는 상태입니다.");
        }

        this.status = Status.ORDER_COMPLETE;
    }

    public boolean isPaidOrder() {
        return this.status != Status.INIT;
    }

    public void deliveryPrepare() {
        if (this.status != Status.ORDER_COMPLETE) throw new IllegalStatusException();
        this.status = Status.DELIVERY_PREPARE;
    }

    public void inDelivery() {
        if (this.status != Status.DELIVERY_PREPARE) throw new IllegalStatusException();
        this.status = Status.IN_DELIVERY;
    }

    public void deliveryComplete() {
        if (this.status != Status.IN_DELIVERY) throw new IllegalStatusException();
        this.status = Status.DELIVERY_COMPLETE;
    }

    public void updateDeliveryInfo(Delivery deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }
}
