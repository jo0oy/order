package dev.jo0oy.order.domain.item;

import dev.jo0oy.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSetFactory itemOptionSetFactory;

    @Override
    @Transactional
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        // Partner 조회
        var partner = partnerReader.getPartner(partnerToken);
        var item = command.toEntity(partner.getId());
        var storedItem = itemStore.store(item);
        itemOptionSetFactory.store(command, item);
        return storedItem.getItemToken();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.MainInfo getItemInfo(String itemToken) {
        log.info("ItemService 상품 상세 조회 로직 실행");
        var item = itemReader.getItemWithOptionGroups(itemToken);
        var itemOptionSet = itemReader.getItemOptionSet(item);
        return new ItemInfo.MainInfo(item, itemOptionSet);
    }

    @Override
    @Transactional
    public void changeOnSales(String itemToken) {
        var item = itemReader.getItem(itemToken);
        item.changeOnSales();
    }


    @Override
    @Transactional
    public void changeEndOfSales(String itemToken) {
        var item = itemReader.getItem(itemToken);
        item.changeEndOfSales();
    }
}
