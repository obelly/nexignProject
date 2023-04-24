package ru.nexign.hrsservice.dto;

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

    @Override
    public String toString() {
        return
                numberPhone +
                ", " + cost;
    }
}
