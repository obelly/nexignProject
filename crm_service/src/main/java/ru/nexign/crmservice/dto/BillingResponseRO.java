package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BillingResponseRO {
    private String phoneNumber;
    private Double balance;
}
