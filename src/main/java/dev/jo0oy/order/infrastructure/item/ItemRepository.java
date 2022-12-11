package dev.jo0oy.order.infrastructure.item;

import dev.jo0oy.order.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemToken(String itemToken);

    @Query("select distinct i from Item i left join fetch i.itemOptionGroups " +
            "where i.itemToken = :itemToken")
    Optional<Item> findByItemTokenFetchJoin(String itemToken);
}
