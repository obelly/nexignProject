package ru.nexign.hrsservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Перечисление типов вызовов.
 *
 * @author Lds
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CallTypeEnum {

    INCOMING("01"),
    OUTGOING("02");

    String number;

    private static final Map<String, CallTypeEnum> mapCallType = new HashMap<>();

    static {
        Arrays.stream(CallTypeEnum.values())
                .forEach(callTypeEnum -> mapCallType.put(callTypeEnum.getNumber(), callTypeEnum));
    }

    public static CallTypeEnum getByNumber(String number) {
        return mapCallType.get(number);
    }
}
