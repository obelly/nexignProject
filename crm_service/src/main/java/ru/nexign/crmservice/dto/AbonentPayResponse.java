package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class AbonentPayResponse {
    private Long id;
    private String numberPhone;
    private Double balance;
}
