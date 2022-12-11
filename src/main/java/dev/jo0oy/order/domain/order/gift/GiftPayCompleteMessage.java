package dev.jo0oy.order.domain.order.gift;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class GiftPayCompleteMessage {
    private final String orderToken;
}
