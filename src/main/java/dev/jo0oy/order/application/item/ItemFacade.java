package dev.jo0oy.order.application.item;

import dev.jo0oy.order.domain.item.ItemCommand;
import dev.jo0oy.order.domain.item.ItemInfo;
import dev.jo0oy.order.domain.item.ItemService;
import dev.jo0oy.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemFacade {

    private final ItemService itemService;
    private final NotificationService notificationService;

    // 상품 등록
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {
        log.info("register item");
        String itemToken = itemService.registerItem(request, partnerToken);
        notificationService.sendEmail(null, null, null);

        return itemToken;
    }

    // 상품 조회
    public ItemInfo.MainInfo getItemInfo(String itemToken) {
        return itemService.getItemInfo(itemToken);
    }

    // 상품 상태 변경
    public void changeOnSales(String itemToken) {
        itemService.changeOnSales(itemToken);
    }

    public void changeEndOfSales(String itemToken) {
        itemService.changeEndOfSales(itemToken);
    }

}
