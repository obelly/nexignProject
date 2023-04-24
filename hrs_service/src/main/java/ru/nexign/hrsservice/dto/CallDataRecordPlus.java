package ru.nexign.hrsservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.nexign.hrsservice.enums.CallTypeEnum;
import ru.nexign.hrsservice.enums.TariffTypeEnum;

import java.time.LocalDateTime;

/**
 * Сущность CDR+ - формат файла, содержащего в себе информацию
 * о времени, стоимости,типа вызова и тариф абонента.
 *
 */
@Getter
@Setter
public class CallDataRecordPlus {

    private String numberPhone;
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TariffTypeEnum tariffType;

}
