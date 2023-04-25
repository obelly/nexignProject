package ru.nexign.brtservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BillingResponse {
    private String phoneNumber;
    private Double balance;
}
