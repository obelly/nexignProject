package ru.nexign.crmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AbonentCallsResponse {
    private Long id;
    private String numberPhone;
    private String tariffIndex;
    private List<CallResponse> payload;
    private Double totalCost;
    private String monetaryUnit;
}
