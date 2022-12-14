package dev.jo0oy.order.interfaces.order;

import dev.jo0oy.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest {
        @NotNull(message = "userId 는 필수값입니다")
        private Long userId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "receiverName 는 필수값입니다")
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private String receiverPhone;

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        private String receiverZipcode;

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        private String receiverAddress1;

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private String etcMessage;

        private List<RegisterOrderItemRequest> orderItemRequests;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemRequest {
        @NotNull(message = "orderQuantity 는 필수값입니다")
        private Integer orderQuantity;

        @NotBlank(message = "itemToken 는 필수값입니다")
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        private Long itemPrice;

        private List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroupRequests;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionGroupRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private String itemOptionGroupName;

        private List<RegisterOrderItemOptionRequest> orderItemOptionRequests;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private Long itemOptionPrice;
    }

    @Getter
    @Setter
    @ToString
    public static class PaymentRequest {
        @NotBlank(message = "orderToken 는 필수값입니다")
        private String orderToken;

        @NotNull(message = "payMethod 는 필수값입니다")
        private PayMethod payMethod;

        @NotNull(message = "amount 는 필수값입니다")
        private Long amount;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderResponse {
        private String orderToken;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderInfoResponse {
        private final String orderToken;
        private final Long userId;
        private final String payMethod;
        private final Long totalAmount;
        private final DeliveryInfoResponse deliveryInfo;
        private final String orderedAt;
        private final String status;
        private final String statusDescription;
        private final List<OrderItemInfoResponse> orderItemInfoResponses;

    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemInfoResponse {
        private final Integer orderQuantity;
        private final Long partnerId;
        private final Long itemId;
        private final String itemName;
        private final Long totalAmount;
        private final Long itemPrice;
        private final List<OrderItemOptionGroupResponse> orderItemOptionGroupInfoResponses;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOptionGroupResponse {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<OrderItemOptionResponse> orderItemOptionInfoResponses;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOptionResponse {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class DeliveryInfoResponse {
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
    }

}
