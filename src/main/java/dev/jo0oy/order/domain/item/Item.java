package dev.jo0oy.order.domain.item;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.common.util.TokenGenerator;
import dev.jo0oy.order.domain.BaseTimeEntity;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "items")
@Entity
public class Item extends BaseTimeEntity {

    private static final String PREFIX_ITEM = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemToken;

    private Long partnerId;

    private String itemName;

    private Long itemPrice;

    @BatchSize(size = 500)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {

        if(Objects.isNull(partnerId)) throw new InvalidParamException("item.partnerId");
        if(StringUtils.isEmpty(itemName)) throw new InvalidParamException("item.itemName");
        if(Objects.isNull(itemPrice)) throw new InvalidParamException("item.itemPrice");

        this.partnerId = partnerId;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }

    public void changeOnSales() {
        this.status = Status.ON_SALES;
    }

    public void changeEndOfSales() {
        this.status = Status.END_OF_SALES;
    }

    public boolean availableSales() {
        return this.status == Status.ON_SALES;
    }
}
