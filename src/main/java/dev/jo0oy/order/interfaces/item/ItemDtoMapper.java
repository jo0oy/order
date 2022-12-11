package dev.jo0oy.order.interfaces.item;

import dev.jo0oy.order.domain.item.ItemCommand;
import dev.jo0oy.order.domain.item.ItemInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR

)
public interface ItemDtoMapper {

    // Register
    // Dto -> Command(Request)
    @Mappings(@Mapping(source = "request.itemOptionGroupRequests", target = "itemOptionGroups"))
    ItemCommand.RegisterItemRequest toCommand(ItemDto.RegisterItemRequest request);

    @Mappings(@Mapping(source = "itemOptionRequests", target = "itemOptions"))
    ItemCommand.RegisterItemOptionGroupRequest toCommand(ItemDto.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest toCommand(ItemDto.RegisterItemOptionRequest request);

    // -> Dto(Response)
    ItemDto.RegisterItemResponse toResponseDto(String itemToken);

    // getInfo
    // info -> Dto(Response)
    @Mappings(@Mapping(source = "itemOptionGroupInfos", target = "itemOptionGroupInfoResponses"))
    ItemDto.ItemInfoResponse toResponseDto(ItemInfo.MainInfo info);

    @Mappings(@Mapping(source = "itemOptionInfos", target = "itemOptionInfoResponses"))
    ItemDto.ItemOptionGroupInfoResponse toResponseDto(ItemInfo.ItemOptionGroupInfo info);

    ItemDto.ItemOptionInfoResponse toResponseDto(ItemInfo.ItemOptionInfo info);
}
