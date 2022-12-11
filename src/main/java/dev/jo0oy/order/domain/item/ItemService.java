package dev.jo0oy.order.domain.item;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken);

    ItemInfo.MainInfo getItemInfo(String itemToken);

    void changeOnSales(String itemToken);

    void changeEndOfSales(String itemToken);
}
