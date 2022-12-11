package dev.jo0oy.order.infrastructure.item.option;

import dev.jo0oy.order.domain.item.option.ItemOption;
import dev.jo0oy.order.domain.item.option.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionStoreImpl implements ItemOptionStore {

    private final ItemOptionRepository itemOptionRepository;

    @Override
    @Transactional
    public void store(ItemOption itemOption) {
        log.info("ItemOptionStore store 로직 실행");
        itemOptionRepository.save(itemOption);
    }
}
