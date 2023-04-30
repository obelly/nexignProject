package ru.nexign.brtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String numberPhone;
    private String tariffId;
    private Double balance;
}
