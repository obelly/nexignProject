package ru.nexign.crmservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String numberPhone;
    private String tariffId;
    private Double balance;
}
