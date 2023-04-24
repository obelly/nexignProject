package ru.nexign.brtservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class ChangeBalance implements Serializable {

    private String numberPhone;
    private Double cost;
}
