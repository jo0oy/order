package dev.jo0oy.order.interfaces.order.gift;

import dev.jo0oy.order.domain.order.OrderCommand;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GiftOrderDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderReq {
        @NotNull(message = "buyerUserId 는 필수값입니다")
        private Long buyerUserId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        private List<RegisterOrderItemReq> orderItemList;

        private String receiverName = "TEMP_VALUE";
        private String receiverPhone = "TEMP_VALUE";
        private String receiverZipcode = "TEMP_VALUE";
        private String receiverAddress1 = "TEMP_VALUE";
        private String receiverAddress2 = "TEMP_VALUE";
        private String etcMessage = "TEMP_VALUE";
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemReq {
        @NotNull(message = "orderQuantity 는 필수값입니다")
        private Integer orderQuantity;

        @NotBlank(message = "itemToken 는 필수값입니다")
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        private Long itemPrice;

        private List<RegisterOrderItemOptionGroupReq> orderItemOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionGroupReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private String itemOptionGroupName;

        private List<RegisterOrderItemOptionReq> orderItemOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private Long itemOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String orderToken;
    }

    @Getter
    @Setter
    @ToString
    public static class PaymentReq {
        @NotBlank(message = "orderToken 는 필수값입니다")
        private String orderToken;

        @NotNull(message = "payMethod 는 필수값입니다")
        private PayMethod payMethod;

        @NotNull(message = "amount 는 필수값입니다")
        private Long amount;

        @NotBlank(message = "orderDescription 는 필수값입니다")
        private String orderDescription;
    }

    @Getter
    @Setter
    @ToString
    public static class UpdateReceiverInfoReq {
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessage;

        public OrderCommand.UpdateReceiverInfoReq toCommand() {
            return OrderCommand.UpdateReceiverInfoReq.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();
        }
    }
}