package ru.nexign.hrsservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChangeBalance {
    private String phoneNumber;
    private Double cost;
}
