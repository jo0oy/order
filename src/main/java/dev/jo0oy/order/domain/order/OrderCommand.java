package dev.jo0oy.order.domain.order;

import dev.jo0oy.order.domain.item.Item;
import dev.jo0oy.order.domain.order.delivery.Delivery;
import dev.jo0oy.order.domain.order.orderItem.OrderItem;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOption;
import dev.jo0oy.order.domain.order.orderItem.OrderItemOptionGroup;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OrderCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOrder {
        private final Long userId;
        private final PayMethod payMethod;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
        private final List<RegisterOrderItem> orderItemList;

        public Order toEntity() {
            var deliveryInfo = Delivery.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();

            return Order.builder()
                    .userId(userId)
                    .payMethod(payMethod)
                    .deliveryInfo(deliveryInfo)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItem {
        private final Integer orderQuantity;
        private final String itemToken;
        private final String itemName;
        private final Long itemPrice;
        private final List<RegisterOrderItemOptionGroup> orderItemOptionGroupList;

        public OrderItem toEntity(Order order, Item item) {
            return OrderItem.builder()
                    .order(order)
                    .orderQuantity(orderQuantity)
                    .partnerId(item.getPartnerId())
                    .itemId(item.getId())
                    .itemToken(itemToken)
                    .itemName(itemName)
                    .itemPrice(itemPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOptionGroup {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<RegisterOrderItemOption> orderItemOptionList;

        public OrderItemOptionGroup toEntity(OrderItem orderItem) {
            return OrderItemOptionGroup.builder()
                    .orderItem(orderItem)
                    .ordering(ordering)
                    .itemOptionGroupName(itemOptionGroupName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOption {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;

        public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
            return OrderItemOption.builder()
                    .orderItemOptionGroup(orderItemOptionGroup)
                    .ordering(ordering)
                    .itemOptionName(itemOptionName)
                    .itemOptionPrice(itemOptionPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentReq {
        private final String orderToken;
        private final PayMethod payMethod;
        private final Long amount;
    }

    @Getter
    @Builder
    @ToString
    public static class UpdateReceiverInfoReq {
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;

        public Delivery toDelivery() {
            return Delivery.builder()
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
