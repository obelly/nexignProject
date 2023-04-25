package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BillingResponse {
    private String phoneNumber;
    private Double balance;
}
