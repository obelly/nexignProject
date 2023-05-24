package ru.nexign.crmservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingResponse {
    private String phoneNumber;
    private String balance;
}
