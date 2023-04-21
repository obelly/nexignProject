package ru.nexign.hrsservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.nexign.hrsservice.entity.Tariff;
import ru.nexign.hrsservice.enums.CallTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сущность CDR+ - формат файла, содержащего в себе информацию
 * о времени, стоимости,типа вызова и тариф абонента.
 *
 * @author Lds
 */
@Getter
@Setter
public class CallDataRecordPlus {

    private String numberPhone;
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Tariff tariff;

    public String toCdrPlusString() {
        return String.format("%s, %s, %s, %s, %s",
                callType.getNumber(),
                numberPhone,
                startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                endTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                tariff.getTariffIndex());
    }

}
