package dev.jo0oy.order.domain.order.orderItem;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.BaseTimeEntity;
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
@Table(name = "order_item_option_groups")
@Entity
public class OrderItemOptionGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemOptionGroupName;

    private Integer ordering;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @BatchSize(size = 500)
    @OneToMany(mappedBy = "orderItemOptionGroup", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemOption> orderItemOptions;

    @Builder
    public OrderItemOptionGroup(String itemOptionGroupName,
                                Integer ordering, OrderItem orderItem) {

        if (Objects.isNull(ordering)) throw new InvalidParamException("OrderItemOptionGroup.ordering");
        if (StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParamException("OrderItemOptionGroup.itemOptionGroupName");
        if (Objects.isNull(orderItem)) throw new InvalidParamException("OrderItemOptionGroup.orderItem");

        this.itemOptionGroupName = itemOptionGroupName;
        this.ordering = ordering;
        this.orderItem = orderItem;
    }

    public Long calculateTotalAmount() {
        return this.orderItemOptions.stream()
                .mapToLong(OrderItemOption::getItemOptionPrice)
                .sum();
    }
}
