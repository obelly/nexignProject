package ru.nexign.brtservice.ro;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseRO {
    private String numberPhone;
    private String tariffId;
    private Double balance;
}
