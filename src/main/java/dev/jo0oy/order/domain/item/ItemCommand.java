package dev.jo0oy.order.domain.item;

import dev.jo0oy.order.domain.item.option.ItemOption;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class ItemCommand {

    @Builder
    @Getter
    @AllArgsConstructor
    @ToString
    public static class RegisterItemRequest {

        private String itemName;
        private Long itemPrice;
        private List<RegisterItemOptionGroupRequest> itemOptionGroups;

        public Item toEntity(Long partnerId) {
            return Item.builder()
                    .partnerId(partnerId)
                    .itemName(itemName)
                    .itemPrice(itemPrice)
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @ToString
    public static class RegisterItemOptionGroupRequest {

        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterItemOptionRequest> itemOptions;

        public ItemOptionGroup toEntity(Item item) {
            return ItemOptionGroup.builder()
                    .item(item)
                    .itemOptionGroupName(itemOptionGroupName)
                    .ordering(ordering)
                    .build();
        }
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @ToString
    public static class RegisterItemOptionRequest {

        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;

        public ItemOption toEntity(ItemOptionGroup itemOptionGroup) {
            return ItemOption.builder()
                    .ordering(ordering)
                    .itemOptionGroup(itemOptionGroup)
                    .itemOptionName(itemOptionName)
                    .itemOptionPrice(itemOptionPrice)
                    .build();
        }
    }
}
