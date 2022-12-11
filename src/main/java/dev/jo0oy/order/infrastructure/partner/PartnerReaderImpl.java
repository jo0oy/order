package dev.jo0oy.order.infrastructure.partner;

import dev.jo0oy.order.common.exception.EntityNotFoundException;
import dev.jo0oy.order.domain.partner.Partner;
import dev.jo0oy.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PartnerReaderImpl implements PartnerReader {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(Long partnerId) {
        return partnerRepository.findById(partnerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository.findByPartnerToken(partnerToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
