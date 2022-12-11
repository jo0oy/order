package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.domain.order.delivery.Delivery;
import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOption;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOptionGroup;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderInfoMapper {


    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "order.deliveryInfo", target = "deliveryInfo"),
            @Mapping(source = "order.orderItems", target = "orderItemInfos"),
            @Mapping(target = "totalAmount", expression = "java(order.calculateTotalAmount())"),
            @Mapping(target = "status", expression = "java(order.getStatus().name())"),
            @Mapping(target = "statusDescription", expression = "java(order.getStatus().getDescription())")
    })
    OrderInfo.MainInfo toInfo(Order order);

    @Mappings({
            @Mapping(source = "orderItem.orderItemOptionGroups", target = "orderItemOptionGroupInfos"),
            @Mapping(target = "totalAmount", expression = "java(orderItem.calculateTotalAmount())")
    })
    OrderInfo.OrderItemInfo toInfo(OrderItem orderItem);

    @Mappings(@Mapping(source = "orderItemOptionGroup.orderItemOptions", target = "orderItemOptionInfos"))
    OrderInfo.OrderItemOptionGroupInfo toInfo(OrderItemOptionGroup orderItemOptionGroup);

    OrderInfo.OrderItemOptionInfo toInfo(OrderItemOption orderItemOption);

    OrderInfo.DeliveryInfo toInfo(Delivery delivery);
}
