package ru.nexign.brtservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.nexign.brtservice.enums.TariffTypeEnum;

@Getter
@Setter
@Builder
public class UserRequestRO {
    private String numberPhone;
    private TariffTypeEnum tariff;
    private Double balance;
}
