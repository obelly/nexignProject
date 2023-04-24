package ru.nexign.brtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseRO {
    private String numberPhone;
    private String tariff;
    private Double balance;
}
