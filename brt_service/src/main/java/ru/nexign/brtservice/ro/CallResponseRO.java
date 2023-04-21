package ru.nexign.brtservice.ro;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nexign.brtservice.enums.CallTypeEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CallResponseRO {
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalTime duration;
    private Double cost;
}