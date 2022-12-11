package dev.jo0oy.order.domain.item.optionGroup;

import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.domain.BaseTimeEntity;
import dev.jo0oy.order.domain.item.Item;
import dev.jo0oy.order.domain.item.option.ItemOption;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_option_groups")
@Entity
public class ItemOptionGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemOptionGroupName;

    private Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @BatchSize(size = 500)
    @OneToMany(mappedBy = "itemOptionGroup",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOption> itemOptions = new ArrayList<>();

    @Builder
    public ItemOptionGroup(String itemOptionGroupName, Integer ordering, Item item) {
        if(StringUtils.isBlank(itemOptionGroupName))
            throw new InvalidParamException("itemOptionGroup.itemOptionGroupName");
        if(Objects.isNull(ordering)) throw new InvalidParamException("itemOptionGroup.ordering");
        if(Objects.isNull(item)) throw new InvalidParamException("itemOptionGroup.item{");

        this.itemOptionGroupName = itemOptionGroupName;
        this.ordering = ordering;
        this.item = item;
    }

    // 연관관계 메서드
    public void addItemOption(ItemOption itemOption) {
        this.itemOptions.add(itemOption);
        itemOption.setItemOptionGroup(this);
    }
}
