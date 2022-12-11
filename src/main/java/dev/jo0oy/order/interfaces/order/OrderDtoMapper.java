package dev.jo0oy.order.interfaces.order;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.OrderInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    // Request
    // Dto -> Command

    @Mappings(@Mapping(source = "request.orderItemRequests", target = "orderItemList"))
    OrderCommand.RegisterOrder toCommand(OrderDto.RegisterOrderRequest request);

    @Mappings(@Mapping(source = "request.orderItemOptionGroupRequests", target = "orderItemOptionGroupList"))
    OrderCommand.RegisterOrderItem toCommand(OrderDto.RegisterOrderItemRequest request);

    @Mappings(@Mapping(source = "request.orderItemOptionRequests", target = "orderItemOptionList"))
    OrderCommand.RegisterOrderItemOptionGroup toCommand(OrderDto.RegisterOrderItemOptionGroupRequest request);

    OrderCommand.RegisterOrderItemOption toCommand(OrderDto.RegisterOrderItemOptionRequest request);

    OrderCommand.PaymentReq toCommand(OrderDto.PaymentRequest request);

    // Response
    // Info -> Dto

    OrderDto.RegisterOrderResponse toResponseDto(String orderToken);

    @Mappings({
            @Mapping(source = "info.orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "info.orderItemInfos", target = "orderItemInfoResponses")
    })
    OrderDto.OrderInfoResponse toResponseDto(OrderInfo.MainInfo info);

    @Mappings(@Mapping(source = "info.orderItemOptionGroupInfos", target = "orderItemOptionGroupInfoResponses"))
    OrderDto.OrderItemInfoResponse toResponseDto(OrderInfo.OrderItemInfo info);

    @Mappings(@Mapping(source = "info.orderItemOptionInfos", target = "orderItemOptionInfoResponses"))
    OrderDto.OrderItemOptionGroupResponse toResponseDto(OrderInfo.OrderItemOptionGroupInfo info);

    OrderDto.OrderItemOptionResponse toResponseDto(OrderInfo.OrderItemOptionInfo info);

    OrderDto.DeliveryInfoResponse toResponseDto(OrderInfo.DeliveryInfo info);
}
