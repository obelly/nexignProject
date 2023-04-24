package ru.nexign.brtservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nexign.brtservice.enums.CallTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сущность CDR+ - формат файла, содержащего в себе информацию
 * о времени, стоимости,типа вызова и тариф абонента.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CallDataRecordPlus {

    private String numberPhone;
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tariffType;

    public String toCdrPlusString() {
        return String.format("%s, %s, %s, %s, %s%n",
                callType.getNumber(),
                numberPhone,
                startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                endTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                tariffType);
    }

}
