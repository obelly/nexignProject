package ru.nexign.crmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CallResponse {
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalTime duration;
    private Double cost;
}
