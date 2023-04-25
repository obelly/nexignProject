package ru.nexign.brtservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nexign.brtservice.enums.TariffTypeEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String numberPhone;
    private String tariff;
    private Double balance;
}
