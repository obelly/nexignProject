package ru.nexign.crmservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TariffResponse {
    private Long id;
    private String numberPhone;
    private String tariffId;
}
