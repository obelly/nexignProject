package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TariffResponseRO {
    private Long id;
    private String numberPhone;
    private String tariff;
}
