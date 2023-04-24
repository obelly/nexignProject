package ru.nexign.hrsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nexign.hrsservice.enums.TariffTypeEnum;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AbonentCallsResponseRO {
    private Long id;
    private String numberPhone;
    private TariffTypeEnum tariffIndex;
    private List<CallResponseRO> payload;
    private Double totalCost;
    private String monetaryUnit;
}
