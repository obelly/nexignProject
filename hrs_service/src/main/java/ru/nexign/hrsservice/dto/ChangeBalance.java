package ru.nexign.hrsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeBalance {
    private String numberPhone;
    private Double cost;

    @Override
    public String toString() {
        return
                numberPhone + ", " + cost;
    }
}
