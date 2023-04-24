package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TariffRequestRO {
    private String numberPhone;
    private String tariffId;
}
