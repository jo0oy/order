package dev.jo0oy.order.interfaces.order.gift;

import dev.jo0oy.order.domain.order.OrderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftOrderDtoMapper {

    // DTO -> Command
    @Mapping(source = "buyerUserId", target = "userId")
    OrderCommand.RegisterOrder toCommand(GiftOrderDto.RegisterOrderReq request);

    OrderCommand.RegisterOrderItem toCommand(GiftOrderDto.RegisterOrderItemReq request);

    OrderCommand.RegisterOrderItemOptionGroup toCommand(GiftOrderDto.RegisterOrderItemOptionGroupReq request);

    OrderCommand.RegisterOrderItemOption toCommand(GiftOrderDto.RegisterOrderItemOptionReq request);

    OrderCommand.PaymentReq toCommand(GiftOrderDto.PaymentReq request);

    OrderCommand.UpdateReceiverInfoReq toCommand(GiftOrderDto.UpdateReceiverInfoReq request);

    // orderToken -> DTO
    GiftOrderDto.RegisterResponse toDto(String orderToken);
}
