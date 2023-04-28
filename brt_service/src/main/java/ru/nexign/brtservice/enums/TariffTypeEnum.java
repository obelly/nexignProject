package ru.nexign.brtservice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TariffTypeEnum {
    @JsonProperty("06")
    UNLIMITED("06", 1.0),
    @JsonProperty("03")
    PER_MINUTE("03", 1.5),
    @JsonProperty("11")
    REGULAR("11", 0.5);
    String number;
    double priceRubMin;
    static final Map<String, TariffTypeEnum> tariffTypeEnumMap = new HashMap<>();

    static {
        Arrays.stream(TariffTypeEnum.values())
                .forEach(callTypeEnum -> tariffTypeEnumMap.put(callTypeEnum.getNumber(), callTypeEnum));
    }
    public static TariffTypeEnum getByNumber(String number) {
        return tariffTypeEnumMap.get(number);
    }

}
