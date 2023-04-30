package ru.nexign.hrsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AbonentCallsResponse {
    private Long id;
    private String numberPhone;
    private String tariffIndex;
    private List<CallResponse> payload;
    private Double totalCost;
    private String monetaryUnit;
}
