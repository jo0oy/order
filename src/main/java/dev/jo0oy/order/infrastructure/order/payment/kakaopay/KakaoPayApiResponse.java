package dev.jo0oy.order.infrastructure.order.payment.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

public class KakaoPayApiResponse {

    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Ready {
        private String tid;
        private String nextRedirectPcUrl;
        private String partnerOrderId;
        private ZonedDateTime createdAt;
    }


    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Amount {
        // 전체 결제 금액
        private Integer total;
        // 비과세 금액
        private Integer taxFree;
        // 부가세 금액
        private Integer vat;
        // 사용할 포인트 금액
        private Integer point;
        // 할인 금액
        private Integer discount;
    }

    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CardInfo {
        // 매입 카드사 한글명
        private String purchaseCorp;
        // 매입 카드사 코드
        private String purchaseCorpCode;
        // 카드 발급사 한글명
        private String issuerCorp;
        // 카드 발급사 코드
        private String issuerCorpCode;
        // 카카오페이 매입사명
        private String kakaopayPurchaseCorp;
        // 카카오페이 매입사 코드
        private String kakaopayPurchaseCorpCode;
        // 카카오페이 발급사명
        private String kakaopayIssuerCorp;
        // 카카오페이 발급사 코드
        private String kakaopayIssuerCorpCode;
        // 카드 BIN
        private String bin;
        // 카드 타입
        private String cardType;
        // 할부 개월 수
        private String installMonth;
        // 카드사 승인번호
        private String approvedId;
        // 카드사 가맹점 번호
        private String cardMid;
        // 무이자할부 여부(Y/N)
        private String interestFreeInstall;
        // 카드 상품 코드
        private String cardItemCode;
    }

    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Approval {
        private String aid;
        private String tid;
        private String cid;
        private String sid;
        private String partnerOrderId;
        private String partnerUserId;
        private String paymentMethodType;
        private String itemName;
        private String itemCode;
        private Integer quantity;
        private ZonedDateTime createdAt;
        private ZonedDateTime approvedAt;
        private String payload;
        private Amount amount;
        private CardInfo cardInfo;
    }

}
