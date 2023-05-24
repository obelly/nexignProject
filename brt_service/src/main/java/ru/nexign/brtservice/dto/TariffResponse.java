package ru.nexign.brtservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TariffResponse {
    private Long id;
    private String numberPhone;
    private String tariffId;
}
