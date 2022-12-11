package dev.jo0oy.order.interfaces.partner;

import dev.jo0oy.order.domain.partner.Partner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PartnerDto {

    @Setter
    @Getter
    @ToString
    public static class RegisterRequest {

        @NotBlank(message = "{NotBlank.partnerDto.partnerName}")
        private String partnerName;

        @NotBlank(message = "{NotBlank.partnerDto.businessNo")
        private String businessNo;

        @Email(message = "{Email.partnerDto.email}")
        @NotBlank(message = "{NotBlank.partnerDto.email")
        private String email;
    }

    @Setter
    @Getter
    @ToString
    public static class PartnerInfoResponse {
        private String partnerToken;
        private String partnerName;
        private String businessNo;
        private String email;
        private Partner.Status status;
    }
}
