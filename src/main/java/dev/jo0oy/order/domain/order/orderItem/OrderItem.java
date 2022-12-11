package dev.jo0oy.order.domain.order.orderItem;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.BaseTimeEntity;
import dev.jo0oy.order.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
@Entity
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partnerId;

    private Long itemId;

    private String itemName;

    private String itemToken;

    private Long itemPrice;

    private Integer orderQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @BatchSize(size = 500)
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemOptionGroup> orderItemOptionGroups;

    @Builder
    public OrderItem(Long partnerId, Long itemId, String itemName, String itemToken,
                     Long itemPrice, Integer orderQuantity, Order order) {

        if(Objects.isNull(partnerId)) throw new InvalidParamException("OrderItem.partnerId");
        if(Objects.isNull(itemId) && StringUtils.isEmpty(itemName))
            throw new InvalidParamException("OrderItem.itemId && OrderItem.itemName");
        if(StringUtils.isEmpty(itemToken)) throw new InvalidParamException("OrderItem.itemToken");
        if(Objects.isNull(itemPrice)) throw new InvalidParamException("OrderItem.itemPrice");
        if(Objects.isNull(orderQuantity) || orderQuantity <= 0) throw new InvalidParamException("OrderItem.orderQuantity");
        if(Objects.isNull(order)) throw new InvalidParamException("OrderItem.order");

        this.partnerId = partnerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemToken = itemToken;
        this.itemPrice = itemPrice;
        this.orderQuantity = orderQuantity;
        this.order = order;
    }

    public Long calculateTotalAmount() {
        var totalOptionAmount = this.orderItemOptionGroups
                .stream().mapToLong(OrderItemOptionGroup::calculateTotalAmount)
                .sum();

        return (itemPrice + totalOptionAmount) * orderQuantity;
    }
}
