package ru.nexign.brtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AbonentPayRequestRO {
    private String numberPhone;
    private Double money;
}
