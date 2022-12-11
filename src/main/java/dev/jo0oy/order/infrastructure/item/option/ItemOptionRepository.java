package dev.jo0oy.order.infrastructure.item.option;

import dev.jo0oy.order.domain.item.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
