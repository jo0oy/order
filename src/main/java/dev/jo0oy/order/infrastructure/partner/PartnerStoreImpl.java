package dev.jo0oy.order.infrastructure.partner;

import dev.jo0oy.order.domain.partner.Partner;
import dev.jo0oy.order.domain.partner.PartnerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PartnerStoreImpl implements PartnerStore {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner store(Partner partner) {
        return partnerRepository.save(partner);
    }
}
