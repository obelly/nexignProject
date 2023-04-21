package ru.nexign.brtservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.nexign.brtservice.enums.CallTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сущность CDR - формат файла, содержащего в себе информацию о времени, стоимости и типа вызова абонента.
 *
 * @author Lds
 */
@Getter
@Setter
public class CallDataRecord {

    private String numberPhone;
    private CallTypeEnum callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    public String toCdrString() {
//        return String.format("%s, %s, %s, %s",
//                callType.getNumber(),
//                subscriberNumber,
//                startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
//                endTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
//    }

}
