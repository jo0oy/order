package dev.jo0oy.order.interfaces.item;

import dev.jo0oy.order.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class ItemDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterItemRequest {
        @NotBlank(message = "{NotBlank.itemDto.partnerToken}")
        private String partnerToken;

        @NotBlank(message = "{NotBlank.itemDto.itemName}")
        private String itemName;

        @Min(value = 1000, message = "{Min.itemDto.itemPrice}")
        private Long itemPrice;

        private List<RegisterItemOptionGroupRequest> itemOptionGroupRequests;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterItemOptionGroupRequest {
        @Min(value = 1, message = "{Min.itemDto.ordering}")
        private Integer ordering;

        @NotBlank(message = "{NotBlank.itemDto.itemOptionGroupName}")
        private String itemOptionGroupName;

        private List<RegisterItemOptionRequest> itemOptionRequests;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterItemOptionRequest {
        @Min(value = 1, message = "{Min.itemDto.ordering}")
        private Integer ordering;

        @NotBlank(message = "{NotBlank.itemDto.itemOptionName}")
        private String itemOptionName;

        @Min(value = 100, message = "{Min.itemDto.itemOptionPrice}")
        private Long itemOptionPrice;{}
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterItemResponse {
        private String itemToken;
    }

    @Getter
    @Setter
    @ToString
    public static class ChangeItemStatusRequest {
        @NotBlank(message = "{NotBlank.itemDto.itemToken}")
        private String itemToken;
    }


    @Getter
    @Setter
    @ToString
    public static class ItemInfoResponse {
        private String itemToken;
        private Long partnerId;
        private String itemName;
        private Long itemPrice;
        private Item.Status status;
        private List<ItemOptionGroupInfoResponse> itemOptionGroupInfoResponses;
    }

    @Getter
    @Setter
    @ToString
    public static class ItemOptionGroupInfoResponse {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<ItemOptionInfoResponse> itemOptionInfoResponses;
    }

    @Getter
    @Setter
    @ToString
    public static class ItemOptionInfoResponse {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }
}
