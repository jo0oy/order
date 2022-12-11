package dev.jo0oy.order.domain.partner;

public interface PartnerReader {
    Partner getPartner(Long partnerId);
    Partner getPartner(String partnerToken);
}
