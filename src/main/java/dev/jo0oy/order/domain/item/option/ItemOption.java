package dev.jo0oy.order.domain.item.option;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.BaseTimeEntity;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_options")
@Entity
public class ItemOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemOptionName;

    private Integer ordering;

    private Long itemOptionPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;

    @Builder
    public ItemOption(
            String itemOptionName,
            Integer ordering,
            Long itemOptionPrice,
            ItemOptionGroup itemOptionGroup
    ) {
        if(Objects.isNull(itemOptionGroup)) throw new InvalidParamException("itemOption.itemOptionGroup");
        if(StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("itemOption.itemOptionName");
        if(Objects.isNull(ordering)) throw new InvalidParamException("itemOption.ordering");
        if(Objects.isNull(itemOptionPrice)) throw new InvalidParamException("itemOption.itemOptionPrice");

        this.itemOptionName = itemOptionName;
        this.ordering = ordering;
        this.itemOptionPrice = itemOptionPrice;
        this.itemOptionGroup = itemOptionGroup;
    }

    public void setItemOptionGroup(ItemOptionGroup itemOptionGroup) {
        this.itemOptionGroup = itemOptionGroup;
    }
}
