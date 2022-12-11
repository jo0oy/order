package dev.jo0oy.order.infrastructure.item;

import dev.jo0oy.order.common.exception.EntityNotFoundException;
import dev.jo0oy.order.domain.item.Item;
import dev.jo0oy.order.domain.item.ItemInfo;
import dev.jo0oy.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;

    @Override
    public Item getItem(String itemToken) {
        log.info("ItemReader 상품 조회 실행");
        return itemRepository.findByItemToken(itemToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Item getItemWithOptionGroups(String itemToken) {
        log.info("ItemReader 상품 조회 - 페치 조인 실행");
        return itemRepository.findByItemTokenFetchJoin(itemToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ItemInfo.ItemOptionGroupInfo> getItemOptionSet(Item item) {
        log.info("ItemReader 상품 옵션 세트 조회");
        return item.getItemOptionGroups()
                .stream()
                .map(itemOptionGroup -> {
                    var itemOptions
                            = itemOptionGroup.getItemOptions()
                            .stream()
                            .map(ItemInfo.ItemOptionInfo::new)
                            .collect(Collectors.toList());

                    return new ItemInfo.ItemOptionGroupInfo(itemOptionGroup, itemOptions);
                }).collect(Collectors.toList());
    }
}
