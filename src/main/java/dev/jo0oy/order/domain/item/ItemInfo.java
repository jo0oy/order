package dev.jo0oy.order.domain.item;

import dev.jo0oy.order.domain.item.option.ItemOption;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemInfo {

    @Getter
    @ToString
    public static class MainInfo {
        private final String itemToken;
        private final Long partnerId;
        private final String itemName;
        private final Long itemPrice;
        private final Item.Status status;
        private final List<ItemOptionGroupInfo> itemOptionGroupInfos;

        public MainInfo(Item item, List<ItemOptionGroupInfo> itemOptionGroupInfos) {
            this.itemToken = item.getItemToken();
            this.partnerId = item.getPartnerId();
            this.itemName = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.status = item.getStatus();
            this.itemOptionGroupInfos = Objects.isNull(itemOptionGroupInfos) ?
                    new ArrayList<>() : itemOptionGroupInfos;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionGroupInfo {
        private final String itemOptionGroupName;
        private final Integer ordering;
        private final List<ItemOptionInfo> itemOptionInfos;

        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup,
                                   List<ItemOptionInfo> itemOptionInfos) {
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionInfos = Objects.isNull(itemOptionInfos) ?
                    new ArrayList<>() : itemOptionInfos;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionInfo {
        private final String itemOptionName;
        private final Integer ordering;
        private final Long itemOptionPrice;

        public ItemOptionInfo(ItemOption itemOption) {
            this.itemOptionName = itemOption.getItemOptionName();
            this.ordering = itemOption.getOrdering();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }
}
