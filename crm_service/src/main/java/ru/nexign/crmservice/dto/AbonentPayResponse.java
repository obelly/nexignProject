package ru.nexign.crmservice.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AbonentPayResponse {
    private Long id;
    private String numberPhone;
    private Double balance;
}
