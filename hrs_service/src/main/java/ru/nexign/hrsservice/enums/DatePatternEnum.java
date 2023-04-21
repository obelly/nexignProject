package ru.nexign.hrsservice.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;

/**
 * Перечисление паттернов дат.
 * @author Lds
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum DatePatternEnum {

    YYYY_MM_DD_HH_MM_SS_TOGETHER(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

   DateTimeFormatter format;
}
