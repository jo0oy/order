package dev.jo0oy.order.infrastructure.item.optionGroup;

import dev.jo0oy.order.domain.item.optionGroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository
        extends JpaRepository<ItemOptionGroup, Long> {
}
