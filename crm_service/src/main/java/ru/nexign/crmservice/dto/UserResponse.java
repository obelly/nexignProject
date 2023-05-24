package ru.nexign.crmservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String numberPhone;
    private String tariffId;
    private Double balance;
}
