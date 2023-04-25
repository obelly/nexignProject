package ru.nexign.crmservice.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRequest {
    private String numberPhone;
    private String tariff;
    private Double balance;
}
