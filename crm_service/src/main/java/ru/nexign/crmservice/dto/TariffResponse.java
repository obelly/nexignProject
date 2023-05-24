package ru.nexign.crmservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffResponse {
    private Long id;
    private String numberPhone;
    private String tariffId;
}
