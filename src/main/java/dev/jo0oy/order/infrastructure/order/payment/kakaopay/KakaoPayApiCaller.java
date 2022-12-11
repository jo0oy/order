package dev.jo0oy.order.infrastructure.order.payment.kakaopay;

import dev.jo0oy.order.domain.order.*;
import dev.jo0oy.order.domain.order.payment.PayMethod;
import dev.jo0oy.order.infrastructure.order.payment.PaymentApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoPayApiCaller implements PaymentApiCaller {

    private static final String HOST_URL = "https://kapi.kakao.com";
    private final OrderReader orderReader;

    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    @Override
    public KakaoPayApiResponse.Ready pay(OrderCommand.PaymentReq paymentReq) {
        // TODO - 구현

        return payReady(paymentReq);
    }

    private KakaoPayApiResponse.Ready payReady(OrderCommand.PaymentReq paymentReq) {
        var orderInfo = orderReader.getOrder(paymentReq.getOrderToken());

        var restTemplate = new RestTemplate();
        var headers = getHeaders("adminKey");
        var params = getKaKaoPayReadyParams(orderInfo, paymentReq.getAmount());

        var body = new HttpEntity<>(params, headers);

        KakaoPayApiResponse.Ready readyResponse = null;

        try {
            readyResponse = restTemplate
                    .postForObject(URI.create(HOST_URL + "/v1/payment/ready"), body, KakaoPayApiResponse.Ready.class);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return readyResponse;
    }

    public KakaoPayApiResponse.Approval payApproval(String pgToken, String tid, String orderToken) {
        // TODO - 구현

        var restTemplate = new RestTemplate();
        var headers = getHeaders("adminKey");
        var params = getKaKaoPayApprovalParams(pgToken, tid, orderToken);

        var body = new HttpEntity<>(params, headers);

        KakaoPayApiResponse.Approval approvalResponse = null;

        try {
            approvalResponse = restTemplate
                    .postForObject(URI.create(HOST_URL + "/v1/payment/approve"), body, KakaoPayApiResponse.Approval.class);

//            return readyResponse;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return approvalResponse;
    }

    private MultiValueMap<String, String> getKaKaoPayApprovalParams(String pg_token, String tid, String orderToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", orderToken);
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("pg_token", pg_token);
        return parameters;
    }

    private MultiValueMap<String, String> getKaKaoPayReadyParams(Order order, Long amount) {
        var orderItems = order.getOrderItems();
        String itemName = orderItems.get(0).getItemName();
        if (orderItems.size() > 1) {
            itemName += " 그외 " + (orderItems.size()-1);
        }

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", order.getOrderToken());
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("item_name", itemName);
        parameters.add("quantity", String.valueOf(orderItems.size()));
        parameters.add("total_amount", String.valueOf(amount));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/orders/pay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/orders/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/orders/pay/fail"); // 결제 실패시 넘어갈 url

        return parameters;
    }

    private HttpHeaders getHeaders(String adminKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
    }
}
