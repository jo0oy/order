package dev.jo0oy.order.interfaces.partner;

import dev.jo0oy.order.application.partner.PartnerFacade;
import dev.jo0oy.order.common.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PartnerApiController {

    private final PartnerFacade partnerFacade;
    private final PartnerDtoMapper partnerDtoMapper;

    @PostMapping("/api/v1/partner")
    public ResponseEntity<?> registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request) {
        var data = partnerFacade.registerPartner(partnerDtoMapper.toCommand(request));
        return ResponseEntity.created(URI.create("/api/v1/partner"))
                .body(ResultResponse.res(true, partnerDtoMapper.toResponseDto(data)));
    }

    @GetMapping("/api/v1/partner/{partnerToken}")
    public ResponseEntity<?> getPartnerInfo(@PathVariable("partnerToken") String partnerToken) {
        var data = partnerFacade.getPartnerInfo(partnerToken);
        return ResponseEntity.ok(ResultResponse.res(true, partnerDtoMapper.toResponseDto(data)));
    }

    @PutMapping("/api/v1/partner/{partnerToken}/enable")
    public ResponseEntity<?> enablePartner(@PathVariable("partnerToken") String partnerToken) {
        partnerFacade.enablePartner(partnerToken);
        return ResponseEntity.ok(ResultResponse.res(true));
    }

    @PutMapping("/api/v1/partner/{partnerToken}/disable")
    public ResponseEntity<?> disablePartner(@PathVariable("partnerToken") String partnerToken) {
        partnerFacade.disablePartner(partnerToken);
        return ResponseEntity.ok(ResultResponse.res(true));
    }
}
