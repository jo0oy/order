package dev.jo0oy.order.application.partner;

import dev.jo0oy.order.domain.notification.NotificationService;
import dev.jo0oy.order.domain.partner.PartnerCommand;
import dev.jo0oy.order.domain.partner.PartnerInfo;
import dev.jo0oy.order.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartnerFacade {

    private final PartnerService partnerService;
    private final NotificationService notificationService;

    // 파트너 등록
    public PartnerInfo registerPartner(PartnerCommand request) {
        var partnerInfo = partnerService.registerPartner(request);
        notificationService.sendEmail(null, null, null);

        return partnerInfo;
    }

    // 파트너 조회
    public PartnerInfo getPartnerInfo(String partnerToken) {
        return partnerService.getPartnerInfo(partnerToken);
    }

    // 파트너 상태 변경
    public void enablePartner(String partnerToken) {
        partnerService.enablePartner(partnerToken);
    }

    public void disablePartner(String partnerToken) {
        partnerService.disablePartner(partnerToken);
    }
}
