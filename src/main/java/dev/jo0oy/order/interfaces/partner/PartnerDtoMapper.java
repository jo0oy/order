package dev.jo0oy.order.interfaces.partner;

import dev.jo0oy.order.domain.partner.PartnerCommand;
import dev.jo0oy.order.domain.partner.PartnerInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PartnerDtoMapper {

    // Register
    // Dto -> Command(Request)
    PartnerCommand toCommand(PartnerDto.RegisterRequest request);

    // getInfo
    // Info -> ResponseDto
    PartnerDto.PartnerInfoResponse toResponseDto(PartnerInfo info);
}
