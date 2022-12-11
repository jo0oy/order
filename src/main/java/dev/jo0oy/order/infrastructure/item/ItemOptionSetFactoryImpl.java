package dev.jo0oy.order.infrastructure.item;

import dev.jo0oy.order.domain.item.Item;
import dev.jo0oy.order.domain.item.ItemCommand;
import dev.jo0oy.order.domain.item.ItemOptionSetFactory;
import dev.jo0oy.order.domain.item.option.ItemOptionStore;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemOptionSetFactoryImpl implements ItemOptionSetFactory {

    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item) {
        log.info("ItemOptionSetFactory 로직 실행");

        var itemOptionGroupList = request.getItemOptionGroups();
        if(CollectionUtils.isEmpty(itemOptionGroupList)) return Collections.emptyList();

        return itemOptionGroupList.stream()
                .map(optionGroupRequest -> {
                    // itemOptionGroup 저장
                    log.info("itemOptionGroup 저장");
                    var itemOptionGroup = optionGroupRequest.toEntity(item);
                    var storedOptionGroup = itemOptionGroupStore.store(itemOptionGroup);

                    // itemOption 저장
                    log.info("itemOption 저장");
                    optionGroupRequest.getItemOptions().forEach(optionRequest -> {
                        var itemOption = optionRequest.toEntity(storedOptionGroup);
                        itemOptionStore.store(itemOption);
                    });

                    return storedOptionGroup;
                }).collect(Collectors.toList());
    }
}
