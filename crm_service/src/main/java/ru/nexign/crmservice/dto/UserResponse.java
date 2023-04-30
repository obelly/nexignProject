package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private String numberPhone;
    private String tariffId;
    private Double balance;
}
