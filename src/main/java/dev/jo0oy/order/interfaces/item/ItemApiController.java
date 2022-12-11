package dev.jo0oy.order.interfaces.item;

import dev.jo0oy.order.application.item.ItemFacade;
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
public class ItemApiController {

    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping("/api/v1/item")
    public ResponseEntity<?> registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        var command = itemDtoMapper.toCommand(request);
        var itemToken = itemFacade.registerItem(command, request.getPartnerToken());

        return ResponseEntity.created(URI.create("/api/v1/item"))
                .body(ResultResponse.res(true, itemDtoMapper.toResponseDto(itemToken)));
    }

    @GetMapping("/api/v1/item/{itemToken}")
    public ResponseEntity<?> getItemInfo(@PathVariable("itemToken") String itemToken) {
        var itemInfo = itemFacade.getItemInfo(itemToken);

        return ResponseEntity.ok(ResultResponse.res(true, itemDtoMapper.toResponseDto(itemInfo)));
    }

    @PutMapping("/api/v1/item/change-on-sales")
    public ResponseEntity<?> changeOnSales(@RequestBody @Valid ItemDto.ChangeItemStatusRequest request) {
        itemFacade.changeOnSales(request.getItemToken());

        return ResponseEntity.ok(ResultResponse.res(true));
    }

    @PutMapping("/api/v1/item/change-end-of-sales")
    public ResponseEntity<?> changeEndOfSales(@RequestBody @Valid ItemDto.ChangeItemStatusRequest request) {
        itemFacade.changeEndOfSales(request.getItemToken());

        return ResponseEntity.ok(ResultResponse.res(true));
    }
}
