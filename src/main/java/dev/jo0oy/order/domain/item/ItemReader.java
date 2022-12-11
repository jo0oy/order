package dev.jo0oy.order.domain.item;

import java.util.List;

public interface ItemReader {
    Item getItem(String itemToken);

    Item getItemWithOptionGroups(String itemToken);

    List<ItemInfo.ItemOptionGroupInfo> getItemOptionSet(Item item);
}
