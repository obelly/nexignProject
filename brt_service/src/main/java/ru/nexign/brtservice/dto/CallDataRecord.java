package ru.nexign.brtservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.nexign.brtservice.enums.CallTypeEnum;

import java.time.LocalDateTime;

/**
 * Сущность CDR - формат файла, содержащего в себе информацию о времени,
 * стоимости и типе вызова абонента.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CallDataRecord {

    private String numberPhone;
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
