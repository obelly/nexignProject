package ru.nexign.brtservice.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Numbers {
    List<ChangeBalance> numbers = new ArrayList<>();
}
