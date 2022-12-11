package dev.jo0oy.order.infrastructure.item.optionGroup;

import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemOptionGroupStoreImpl implements ItemOptionGroupStore {

    private final ItemOptionGroupRepository itemOptionGroupRepository;
    
    @Override
    public ItemOptionGroup store(ItemOptionGroup itemOptionGroup) {
        log.info("ItemOptionGroupStore store 로직 실행");
        return itemOptionGroupRepository.save(itemOptionGroup);
    }
}
