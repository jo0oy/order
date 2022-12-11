package dev.jo0oy.order.domain.item;

import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSetFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item);

}
