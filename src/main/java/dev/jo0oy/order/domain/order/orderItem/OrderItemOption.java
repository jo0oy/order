package dev.jo0oy.order.domain.order.orderItem;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item_options")
@Entity
public class OrderItemOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemOptionName;

    private Integer ordering;

    private Long itemOptionPrice;

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroup orderItemOptionGroup;

    @Builder
    public OrderItemOption(String itemOptionName, Integer ordering,
                           Long itemOptionPrice, OrderItemOptionGroup orderItemOptionGroup) {

        if (Objects.isNull(orderItemOptionGroup)) throw new InvalidParamException("OrderItemOption.orderItemOptionGroup");
        if (Objects.isNull(ordering)) throw new InvalidParamException("OrderItemOption.ordering");
        if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException("OrderItemOption.itemOptionName");
        if (Objects.isNull(itemOptionPrice)) throw new InvalidParamException("OrderItemOption.itemOptionPrice");

        this.itemOptionName = itemOptionName;
        this.ordering = ordering;
        this.itemOptionPrice = itemOptionPrice;
        this.orderItemOptionGroup = orderItemOptionGroup;
    }


}
